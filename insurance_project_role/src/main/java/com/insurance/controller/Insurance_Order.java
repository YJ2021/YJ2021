package com.insurance.controller;

import com.insurance.pojo.*;
import com.insurance.service.Insurance_ProService;
import com.insurance.service.Insurance_Pro_ApplyService;
import com.insurance.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/order")
@RestController
@Api("订单相关")
@Slf4j
public class Insurance_Order {

    @Autowired
    Insurance_ProService proService;

    @Autowired
    Insurance_Pro_ApplyService pro_applyService;

    @RequestMapping(value = "/searchUserPro/{proId},{userCode}")
    @ApiOperation("订单用户信息与保险产品信息查询")
    public DTO searchUserPro(HttpServletRequest request, HttpServletResponse response, @PathVariable String proId,@PathVariable String userCode){
        System.out.println("proId:"+proId+" userCode:"+userCode);
        if(EmptyUtils.isNotEmpty(proId)&&EmptyUtils.isNotEmpty(userCode)){
            Insurance_Products production = proService.searchProDetails(proId.trim());
            Insurance_User user = proService.searchUser(userCode.trim());
            log.info("production:"+production);
            log.info("user:"+user);
            if (EmptyUtils.isNotEmpty(production)){
                if(EmptyUtils.isNotEmpty(user)){
                    Insurance_Pro_Order pro_order=new Insurance_Pro_Order();
                    pro_order.setProId(production.getId());
                    pro_order.setProName(production.getProName());
                    pro_order.setProType(production.getProType());
                    pro_order.setDetails(production.getDetails());
                    pro_order.setBuyCondition(production.getBuyCondition());
                    pro_order.setPayAmount(production.getPayAmount()+"");
                    pro_order.setEndDate(production.getEndDate());
                    pro_order.setUserCode(user.getUserCode());
                    pro_order.setUserName(user.getUserName());
                    pro_order.setMail(user.getMail());
                    pro_order.setIdNumber(user.getIdNumber());

                    return  DTOUtil.returnSucess("用户,产品信息查询成功",pro_order);
                }
                else{
                     return DTOUtil.returnFalse("用户信息查询失败",ErrorCode.ORDER_USER_NOT_EXIST);
                }
            }
            else{
                return DTOUtil.returnFalse("保险产品信息查询失败",ErrorCode.ORDER_PRO_NOT_EXIST);
            }
        }
        else {
            return DTOUtil.returnFalse("查询失败", ErrorCode.ORDER_INPUT_EMPTY);
        }

    }

    @RequestMapping(value = "/saveOrder")
    @ApiOperation("保存保险申请订单")
    public DTO saveOrder(HttpServletRequest request, HttpServletResponse response,@RequestBody Insurance_Pro_Order order){
        if(EmptyUtils.isNotEmpty(order)){
            try {
                //订单编号处理
                //生成订单号：机器码 +日期+（MD5）（商品IDs+用户id+毫秒数+1000000的(6位)随机数）
                StringBuilder md5String = new StringBuilder();
                md5String.append(order.getProId());
                md5String.append(order.getUserCode());
                md5String.append(System.currentTimeMillis());
                md5String.append(Math.random() * 1000000);
                String md5 = MD5Util.getMd5(md5String.toString(), 6);
                //生成订单编号
                StringBuilder orderNo = new StringBuilder();
                orderNo.append("D1000001");
                orderNo.append(DateUtil.format(new Date(), "yyyyMMddHHmmss"));
                orderNo.append(md5);
                System.out.println("订单编号："+orderNo.toString());
                order.setOrderNo(orderNo.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //申请时间
            String applyDate=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(new Date());
            order.setApplyDate(applyDate);
            //状态
            //订单状态  0.申请中 1.初审完成 2.初审失败 3.复审失败 4.待支付 5.支付成功 6.生效 7.过期 8.申请赔付中 9.赔付失败 10.已赔付
            order.setStatusType("0");
            System.out.println("order:"+order.toString());
            Integer rows = proService.saveOrder(order);
            if(rows>0){
                Insurance_Pro_Apply pro_apply=new Insurance_Pro_Apply();
                pro_apply.setOrderNo(order.getOrderNo());
                pro_apply.setProStatus("0");
                log.info("订单审核表数据："+pro_apply.toString());
                Integer code = pro_applyService.saveProApply(pro_apply);
                if(code>0){
                    return DTOUtil.returnSucess("订单申请成功");
                }
                else{
                    return DTOUtil.returnFalse("保存订单信息到审核表失败",ErrorCode.ORDER_OTHER);
                }
            }
            else{
                return DTOUtil.returnFalse("订单信息保存失败",ErrorCode.ORDER_OTHER);
            }
        }else{
            return  DTOUtil.returnFalse("订单信息为空",ErrorCode.ORDER_INPUT_EMPTY);
        }
    }

    @RequestMapping(value = "/searchOrderList")
    @ApiOperation("个人订单查询")
    public DTO searchOrderList(@RequestBody Insurance_ProPagination pagination){
           log.info("订单分页信息："+pagination.toString());
           if(EmptyUtils.isNotEmpty(pagination)){
               List<Insurance_Pro_Order> pro_orderList
                       = pro_applyService.searchOrderList(pagination.getUserCode(), pagination.getCurrentPage(), pagination.getPageSize());
               Integer maxRows = pro_applyService.searchMaxRows(pagination.getUserCode());
               if(EmptyUtils.isNotEmpty(pro_orderList)&&maxRows>0){
                   Map<String,Object> map=new HashMap<>();
                   map.put("pro_orderList",pro_orderList);
                   map.put("maxRows",maxRows);
                   return  DTOUtil.returnSucess("订单列表查询成功",map);
               }
               else {
                   return  DTOUtil.returnFalse("订单列表查询失败",ErrorCode.ORDER_PRO_NOT_EXIST);
               }
           }else{
               return  DTOUtil.returnFalse("传入参数为空",ErrorCode.ORDER_INPUT_EMPTY);
           }
    }

    @RequestMapping(value = "/searchProApply")
    @ApiOperation("初审列表查询")
    public DTO searchProApply(@RequestBody Insurance_ProPagination pagination){
        log.info("订单分页信息："+pagination.toString());
        if(EmptyUtils.isNotEmpty(pagination)) {
            Integer[] statusType={0,1,2};
            List<Insurance_Pro_Order> pro_applyList = pro_applyService.searchProApply(statusType,pagination.getCurrentPage(), pagination.getPageSize());
            log.info("初审分页："+pro_applyList);
            Integer maxRows = pro_applyService.searchApplyMaxRows(statusType);
            if(EmptyUtils.isNotEmpty(pro_applyList)&&maxRows>0){
                Map<String,Object> map=new HashMap<>();
                map.put("pro_applyList",pro_applyList);
                map.put("maxRows",maxRows);
                return  DTOUtil.returnSucess("订单审核列表查询成功",map);
                }
            else{
                return  DTOUtil.returnFalse("订单审核列表查询失败",ErrorCode.ORDER_LIST_NOT_EXIST);
            }

            }else{
            return  DTOUtil.returnFalse("传入参数为空",ErrorCode.ORDER_INPUT_EMPTY);
        }
    }

    @RequestMapping("/updateApplyOK/{orderNo}")
    @ApiOperation("初审状态（同意）")
    public DTO updateApplyOK(@PathVariable String orderNo){
        log.info("订单编号："+orderNo);
        if(EmptyUtils.isNotEmpty(orderNo)){
            Insurance_Pro_Order pro_order=new Insurance_Pro_Order();
            pro_order.setOrderNo(orderNo);
            pro_order.setStatusType("1");
            pro_order.setApproverDate(new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(new Date()));
            Integer rows1 = pro_applyService.updateApplyStatus(pro_order);
            Insurance_Pro_Apply pro_apply=new Insurance_Pro_Apply();
            pro_apply.setProStatus("1");
            pro_apply.setOrderNo(orderNo);
            Integer rows2 = pro_applyService.update_ProApplyStatus(pro_apply);
            if(rows1>0&&rows2>0){
                return DTOUtil.returnSucess("审核成功");
            }
            else{
                return DTOUtil.returnFalse("修改用户审核状态失败",ErrorCode.ORDER_OTHER);
            }
        }else{
            return DTOUtil.returnFalse("传入参数为空",ErrorCode.ORDER_INPUT_EMPTY);
        }
    }

    @RequestMapping("/updateApplyFalse/{orderNo}")
    @ApiOperation("初审状态（退件）")
    public DTO updateApplyFalse(@PathVariable String orderNo){
        log.info("订单编号："+orderNo);
        if(EmptyUtils.isNotEmpty(orderNo)){
            Insurance_Pro_Order pro_order=new Insurance_Pro_Order();
            pro_order.setOrderNo(orderNo);
            pro_order.setStatusType("2");
            pro_order.setApproverDate(new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(new Date()));
            Integer rows1 = pro_applyService.updateApplyStatus(pro_order);
            Insurance_Pro_Apply pro_apply=new Insurance_Pro_Apply();
            pro_apply.setProStatus("2");
            pro_apply.setOrderNo(orderNo);
            Integer rows2 = pro_applyService.update_ProApplyStatus(pro_apply);
            if(rows1>0&&rows2>0){
                return DTOUtil.returnSucess("审核成功");
            }
            else{
                return DTOUtil.returnFalse("修改用户审核状态失败",ErrorCode.ORDER_OTHER);
            }
        }else{
            return DTOUtil.returnFalse("传入参数为空",ErrorCode.ORDER_INPUT_EMPTY);
        }
    }

    @RequestMapping(value = "/reRearchProApply")
    @ApiOperation("复审列表查询")
    public DTO reRearchProApply(@RequestBody Insurance_ProPagination pagination){
        log.info("订单分页信息："+pagination.toString());
        if(EmptyUtils.isNotEmpty(pagination)) {
            Integer[] statusType={1,3,4};
            List<Insurance_Pro_Order> pro_applyList = pro_applyService.searchProApply(statusType,pagination.getCurrentPage(), pagination.getPageSize());
            log.info("复审分页："+pro_applyList);
            Integer maxRows = pro_applyService.searchApplyMaxRows(statusType);
            if(EmptyUtils.isNotEmpty(pro_applyList)&&maxRows>0){
                Map<String,Object> map=new HashMap<>();
                map.put("pro_applyList",pro_applyList);
                map.put("maxRows",maxRows);
                BeanUtils.copyProperties("","");//copy没有使用
                return  DTOUtil.returnSucess("订单审核列表查询成功",map);
            }
            else{
                return  DTOUtil.returnFalse("订单审核列表查询失败",ErrorCode.ORDER_LIST_NOT_EXIST);
            }

        }else{
            return  DTOUtil.returnFalse("传入参数为空",ErrorCode.ORDER_INPUT_EMPTY);
        }
    }

    @RequestMapping("/reUpdateApplyOK/{orderNo}")
    @ApiOperation("复审状态（同意）")
    public DTO reUpdateApplyOK(@PathVariable String orderNo){
        log.info("订单编号："+orderNo);
        if(EmptyUtils.isNotEmpty(orderNo)){
            Insurance_Pro_Order pro_order=new Insurance_Pro_Order();
            pro_order.setOrderNo(orderNo);
            pro_order.setStatusType("4");
            pro_order.setApproverDate(new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(new Date()));
            Integer rows1 = pro_applyService.updateApplyStatus(pro_order);
            Insurance_Pro_Apply pro_apply=new Insurance_Pro_Apply();
            pro_apply.setProStatus("3");
            pro_apply.setOrderNo(orderNo);
            Integer rows2 = pro_applyService.update_ProApplyStatus(pro_apply);
            if(rows1>0&&rows2>0){
                return DTOUtil.returnSucess("审核成功");
            }
            else{
                return DTOUtil.returnFalse("修改用户审核状态失败",ErrorCode.ORDER_OTHER);
            }
        }else{
            return DTOUtil.returnFalse("传入参数为空",ErrorCode.ORDER_INPUT_EMPTY);
        }
    }

    @RequestMapping("/reUpdateApplyFalse/{orderNo}")
    @ApiOperation("复审状态（退件）")
    public DTO reUpdateApplyFalse(@PathVariable String orderNo){
        log.info("订单编号："+orderNo);
        if(EmptyUtils.isNotEmpty(orderNo)){
            Insurance_Pro_Order pro_order=new Insurance_Pro_Order();
            pro_order.setOrderNo(orderNo);
            pro_order.setStatusType("3");
            pro_order.setApproverDate(new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(new Date()));
            Integer rows1 = pro_applyService.updateApplyStatus(pro_order);
            Insurance_Pro_Apply pro_apply=new Insurance_Pro_Apply();
            pro_apply.setProStatus("4");
            pro_apply.setOrderNo(orderNo);
            Integer rows2 = pro_applyService.update_ProApplyStatus(pro_apply);
            if(rows1>0&&rows2>0){
                return DTOUtil.returnSucess("审核成功");
            }
            else{
                return DTOUtil.returnFalse("修改用户审核状态失败",ErrorCode.ORDER_OTHER);
            }
        }else{
            return DTOUtil.returnFalse("传入参数为空",ErrorCode.ORDER_INPUT_EMPTY);
        }
    }


}
