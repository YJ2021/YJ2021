package com.insurance.controller;

import com.insurance.pojo.DTO;
import com.insurance.pojo.Insurance_ProPagination;
import com.insurance.pojo.Insurance_Pro_Order;
import com.insurance.pojo.Insurance_Products;
import com.insurance.service.Insurance_ProService;
import com.insurance.util.DTOUtil;
import com.insurance.util.EmptyUtils;
import com.insurance.util.ErrorCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pro")
@Api("保险产品")
@Slf4j
public class Insurance_ProductController {

    @Autowired
    private Insurance_ProService proService;

    @RequestMapping("/searchPro")
    @ApiOperation("查询保险产品")
     public DTO searchPro(HttpServletRequest request, HttpServletResponse response, @RequestBody Insurance_ProPagination proPage){
        try {
            log.info(proPage.toString());
            if(EmptyUtils.isNotEmpty(proPage)){
                List<Insurance_Products> proList = proService.searchPro(proPage.getCurrentPage(), proPage.getPageSize());
                Integer maxRows=proService.searchProMaxRows();
                if(EmptyUtils.isNotEmpty(proList)){
                    Map<String,Object> map=new HashMap<>();
                    for(int i=0;i<proList.size();i++){

                        Insurance_Products product=proList.get(i);
                        System.out.println(product.toString());
                        product.setCreDate(new SimpleDateFormat("yyyy-MM-dd").format(product.getCreationDate()));
                    }
                    map.put("proList",proList);
                    map.put("maxRows",maxRows);
                    return  DTOUtil.returnSucess("保险产品查询成功",map);

                }
                else{
                    return  DTOUtil.returnFalse("保险产品查询为空", ErrorCode.PRO_SEARCH_EMPTY);
                }
            }else{
                return  DTOUtil.returnFalse("参数传入为空",ErrorCode.PRO_INPUT_EMPTY);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return DTOUtil.returnFalse("后台代码异常",e.getMessage());
        }
    }

    @RequestMapping(value = "/searchProDetails/{proId}")
    @ApiOperation("查询保险产品详情")
    public DTO searchProDetails(HttpServletRequest request, HttpServletResponse response, @PathVariable String proId){
        try {
            System.out.println("proId:"+proId);
            if(EmptyUtils.isNotEmpty(proId)){
                Insurance_Products production = proService.searchProDetails(proId);
                System.out.println(production.toString());
                if(EmptyUtils.isNotEmpty(production)){
                        production.setCreDate(new SimpleDateFormat("yyyy-MM-dd").format(production.getCreationDate()));
                    return DTOUtil.returnSucess("保险产品详情查询成功",production);
                }
                else{
                    return DTOUtil.returnFalse("查询保险项目不存在",ErrorCode.PRO_NOT_EXIST);
                }
            }
            else{
                return DTOUtil.returnFalse("传入参数为空",ErrorCode.PRO_INPUT_EMPTY);
            }

        } catch (Exception e) {

            return DTOUtil.returnFalse("保险产品详情查询失败",e.getMessage());
        }
    }

    @ApiOperation("查询个人订单Solr")
    @RequestMapping("/searchProBySolr")
    public  DTO searchProBySolr(){
        List<Insurance_Pro_Order> pro_orderList = proService.searchProBySolr(1, 5, "id", "14");
        Map<String,Object> map=new HashMap<>();
        map.put("pro_orderList",pro_orderList);
        map.put("maxRows",1);
        return DTOUtil.returnSucess("查询成功",map);
    }

    @ApiOperation("查询保险订单Solr")
    @RequestMapping("/searchAllProBySolr")
    public  DTO searchAllProBySolr(){
        List<Insurance_Pro_Order> pro_orderList = proService.searchProBySolr(1, 5, "id", "14");
        Map<String,Object> map=new HashMap<>();
        map.put("pro_orderList",pro_orderList);
        map.put("maxRows",1);
        return DTOUtil.returnSucess("查询成功",map);
    }
}
