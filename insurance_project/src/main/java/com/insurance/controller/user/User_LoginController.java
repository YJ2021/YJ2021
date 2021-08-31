package com.insurance.controller.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.insurance.pojo.DTO;
import com.insurance.pojo.Forget;
import com.insurance.pojo.Insurance_User;
import com.insurance.pojo.UserMessage;
import com.insurance.service.user.User_RegisterService;
import com.insurance.util.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Slf4j
@RequestMapping(value = "/login")
/**
 * 用户登录相关
 */
public class User_LoginController {
    private Jedis jedis=new Jedis("127.0.0.1",6379);
    @Autowired
    User_RegisterService userRegisterService;
    @RequestMapping(value = "/doLogin")
    @ApiOperation("登录验证")
    public DTO login(@RequestBody Insurance_User user, HttpServletRequest request, HttpServletResponse response){
        //判断传入参数是否为空
        if(EmptyUtils.isNotEmpty(user.getUserCode())&&EmptyUtils.isNotEmpty(user.getUserPassword())){
            //密码加密
            String userPassword=MD5Util.getMd5(user.getUserPassword(),32);
            user.setUserPassword(userPassword);
            Insurance_User loginUser = userRegisterService.login(user);
            //判断用户是否存在
            if(EmptyUtils.isNotEmpty(loginUser)){
                //登录成功，判断redis中是否存在（过往两小时有没有登录）
              if(jedis.get(user.getUserCode())!=null){
                 /*一种情况是现在处于上一次登陆的1分钟以内，保护期拒绝本次登录
                      1.同一个浏览器保护期中替换新的token
                      2.不同浏览器需要等待保护期
                    一种情况超过上一次登录的保护期，直接登录，去掉原来登录的权限*/
                  log.info("该账号已经登录...");
                  try {
                      // 1.判断之前登录的时间和浏览器
                      String header=request.getHeader("user-agent");//获取请求头中的信息
                      TokenUtil.replaceToken(header,loginUser.getUserCode(),response);
                      return DTOUtil.returnSucess("重新登录成功",loginUser);
                  } catch (TokenValidationFailedException e) {
                     return DTOUtil.returnFalse(e.getMessage(),ErrorCode.LOGIN_OTHER);
                  }
              }
              else{
                  //浏览器传送的请求
                  String browerRequest=request.getHeader("user-agent");
                  //生成token
                  String token= TokenUtil.getTokenGenerator(browerRequest,loginUser);
                  //缓存token到redis
                  log.info("登录生成的token："+token);
                  if(EmptyUtils.isNotEmpty(token)){
                      //1.token作为key loginUser作为value
                      String jsonUser= JSONObject.toJSONString(loginUser);//转为json字符串
                      jedis.setex(token,7200,jsonUser);
                      //2.loginUser作为key token作为value
                      jedis.setex(loginUser.getUserCode(),7200,token);
                      //将生成的token存储到cookie中
                      Cookie cookie =new Cookie("token",token);
                      cookie.setMaxAge(7200);
                      response.addCookie(cookie);
                      log.info("登陆成功");
                      return DTOUtil.returnSucess("登录成功",loginUser);
                  }
                  else{
                      return DTOUtil.returnFalse("token生成失败，",ErrorCode.LOGIN_BACKGROUND_CODE_ERROR);
                  }
              }
            }
            else{
                return DTOUtil.returnFalse("登录失败，用户不存在", ErrorCode.LOGIN_USER_NOT_EXIST);
            }
        }
        else{
            return DTOUtil.returnFalse("登录失败，输入为空!",ErrorCode.LOGIN_INPUT_EMPTY);
        }

    }
    @RequestMapping(value = "/tokenCheck")
    @ApiOperation("异地/其他浏览器登录验证")
    public DTO tokenCheck(HttpServletRequest request, HttpServletResponse response){
        log.info("验证token有效方法.."+request.getHeader("Cookie"));
        String token=request.getHeader("Cookie");
        String ctoken=null;
        if(token.contains(";")){
            ctoken=token.substring(6,token.indexOf(";"));
        }else{
            ctoken=token.substring(6);
        }

        System.out.println("【ctoken】："+ctoken+"【ctoken】");
        try {
            Insurance_User redisUser = JSONArray.parseObject(jedis.get(ctoken).toString(), Insurance_User.class);
            String userCode=redisUser.getUserCode();
            if(ctoken.equals(jedis.get(userCode))){
                return DTOUtil.returnSucess("身份验证成功");
            }else{
                return DTOUtil.returnFalse("该用户异地登录，本机自动下线", ErrorCode.AUTH_UNKNOWN);
            }
        } catch (Exception e) {
            return DTOUtil.returnFalse("身份验证失败", ErrorCode.AUTH_UNKNOWN);
        }

    }

    @RequestMapping(value = "/forgetPassword")
    @ApiOperation("忘记密码")
    public DTO forgetPassword(@RequestBody Insurance_User user){
             //判断传入参数是否为空
             if(EmptyUtils.isNotEmpty(user.getUserCode())){
                 Insurance_User user1 = userRegisterService.searchUser(user.getUserCode());
                 //判断用户是否存在
                 if(EmptyUtils.isNotEmpty(user)){
                     String oldCode = jedis.get(user.getUserCode());
                     //判断验证码是否过期
                     if(EmptyUtils.isNotEmpty(oldCode)){
                         return DTOUtil.returnFalse("验证码未失效！",ErrorCode.FORGET_CODE_NOT_INVALID);
                     }
                     String emailCode = EmailUtil.emailregister(user1);
                     log.info("emailCode:"+emailCode);
                     //判断验证码是否发送成功
                     if(EmptyUtils.isNotEmpty(emailCode)){
                         jedis.setex(user.getUserCode(),120,emailCode);
                         return  DTOUtil.returnSucess("发送成功！");
                     }
                     else{
                         return  DTOUtil.returnFalse("验证码发送失败",ErrorCode.FORGET_CODE_SEND_ERROR);
                     }
                 }
                 else{
                     return  DTOUtil.returnFalse("用户不存在",ErrorCode.FORGET_USER_NOT_EXIST);
                 }

             }
             else{
                 return  DTOUtil.returnFalse("用户名为空",ErrorCode.FORGET_INPUT_EMPTY);
             }
    }
    @RequestMapping("/initializePassword")
    @ApiOperation("初始化密码")
    public DTO initializePassword(@RequestBody Forget code){
        log.info(code.toString());
            //判断传入参数是否为空
        if(EmptyUtils.isNotEmpty(code)){
            //判断redis中是否存在此用户
            String redisCode = jedis.get(code.getUserCode());
            if(EmptyUtils.isNotEmpty(redisCode)){
                //验证
                if(redisCode.trim().equals(code.getCode())){
                    jedis.del(code.getUserCode());
                    //初始化密码
                    String initializePassword="admin";
                    String md5Password = MD5Util.getMd5(initializePassword, 32);
                    code.setInitializePassword(md5Password);
                    Integer rows = userRegisterService.initializePassword(code);
                    //判断密码是否初始化成功
                    if(rows>0){
                        log.info("初始化成功");
                       return DTOUtil.returnSucess("密码初始化成功！");
                    }else{
                        return DTOUtil.returnFalse("初始化失败，后台代码错误",ErrorCode.FORGET_BACKGROUND_CODE_ERROR);
                    }
                }else{
                    return DTOUtil.returnFalse("验证失败，验证码错误",ErrorCode.FORGET_CODE_ERROR);
                }
            }else {
                return DTOUtil.returnFalse("验证失败，用户名不存在",ErrorCode.FORGET_USER_NOT_EXIST);
            }
        }else {
            return DTOUtil.returnFalse("验证失败，输入为空",ErrorCode.FORGET_INPUT_EMPTY);
        }

    }
    @ApiOperation("退出登录")
    @RequestMapping(value = "/loginOut/{userCode}")
    public DTO loginOut(HttpServletRequest request, HttpServletResponse response, @PathVariable String userCode){
        //判断传入参数是否为空
        if(EmptyUtils.isNotEmpty(userCode)){
              //删除redis的token
            String token = jedis.get(userCode);
            jedis.del(token);
            jedis.del(userCode);
            //删除浏览器的token
            Cookie cookie=new Cookie("token",null);
            cookie.setMaxAge(0);
            cookie.setPath("/auth/login");
            response.addCookie(cookie);
            return  DTOUtil.returnSucess("退出登录成功！");
        }else{
            return DTOUtil.returnFalse("退出登录失败",ErrorCode.LOGINOUT_INPUT_EMPTY);
        }
    }
    @ApiOperation("查询单个用户信息")
    @RequestMapping(value = "/findUserMessage/{userCode}")
    public DTO findUserMessage(HttpServletRequest request, HttpServletResponse response, @PathVariable String userCode){
        /*查询单个也可以前台不传递数据，后台从cookie中获取token，然后从redis中获取查询对象*/
        System.out.println("userCode:"+userCode);
        if(EmptyUtils.isNotEmpty(userCode)){
              Insurance_User user = userRegisterService.searchUser(userCode);
              if(EmptyUtils.isNotEmpty(user)){
                  UserMessage userMessage=new UserMessage();
                  userMessage.setId(user.getId());//ID
                  userMessage.setUserCode(user.getUserCode());//用户名
                  userMessage.setUserPassword("******");//密码
                  userMessage.setMail(user.getMail());//邮箱
                  userMessage.setUserName(user.getUserName());//真实姓名
                  userMessage.setIdNumber(user.getIdNumber());//身份证号
                  Integer userType=user.getUserType();//用户类型
                  userMessage.setCreatedBy(user.getCreatedBy());//创建人
                  //创建时间
                  if(user.getCreationDate()!=null){
                      userMessage.setCreationDate(new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(user.getCreationDate()));
                  }
                  userMessage.setModifiedBy(user.getModifiedBy());//修改人
                  //修改时间
                  if(user.getModifyDate()!=null){
                      userMessage.setModifyDate(new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(user.getModifyDate()));
                  }
                  if(userType==0){
                      userMessage.setUserType("管理员");
                  }else if(userType==1){
                      userMessage.setUserType("自注册用户");
                  }else if(userType==2){
                      userMessage.setUserType("保险销售部门");
                  }else if(userType==3){
                      userMessage.setUserType("风险合规部");
                  }else{
                      userMessage.setUserType("其他");
                  }
                  if(user.getActivated()==1){
                      userMessage.setActivated("已激活");//用户状态
                  }else{
                      userMessage.setActivated("未激活");//用户状态
                  }
                  return  DTOUtil.returnSucess("用户信息查询成功",userMessage);
              }else{
                  return  DTOUtil.returnFalse("用户信息查询失败",ErrorCode.FIND_USER_ERROR);
              }
          }else{
              return  DTOUtil.returnFalse("用户信息查询失败",ErrorCode.FIND_USER_ERROR);
          }
    }
    @ApiOperation("修改单个用户信息")
    @RequestMapping(value = "/updateUserMessage")
    public DTO updateUserMessage(HttpServletRequest request, HttpServletResponse response,@RequestBody UserMessage olduser){
            System.out.println("进入修改用户信息方法...");
            Insurance_User user=new Insurance_User();
            //user.setId(olduser.getId());
            user.setUserCode(olduser.getUserCode());
            //密码加密
           if(!olduser.getUserPassword().equals("******")){
            String md5passwd= MD5Util.getMd5(olduser.getUserPassword(),32);
            user.setUserPassword(md5passwd);
           }
            user.setUserName(olduser.getUserName());
            user.setIdNumber(olduser.getIdNumber());
            user.setMail(olduser.getMail());
            /*if(olduser.getActivated().equals("已激活")){
                user.setActivated(1);
            }else{
                user.setActivated(0);
            }*/
            /*user.setCreatedBy(olduser.getCreatedBy());
            //创建时间
            if(olduser.getCreationDate()!=null){
                try {
                    user.setCreationDate(new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").parse(olduser.getCreationDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }*/
            /*if(olduser.getUserType().equals("管理员")){
                user.setUserType(0);
            }else if(olduser.getUserType().equals("自注册用户")){
                user.setUserType(1);
            }else if(olduser.getUserType().equals("保险销售部门")){
                user.setUserType(2);
            }else if(olduser.getUserType().equals("风险合规部")){
                user.setUserType(3);
            }else{
                user.setUserType(4);
            }*/
            //修改注意修改人和修改时间在这个位置自行手动填入
            user.setModifyDate(new Date());
            user.setModifiedBy(user.getUserName());
            //调用修改方法
            Integer code= userRegisterService.updateUser(user);
            if(code>0){
                System.out.println("修改成功...");
                return DTOUtil.returnSucess("用户信息修改成功",olduser);

        }else{
                return DTOUtil.returnFalse("数据获取失败,后台代码错误",ErrorCode.UPDATE_USER_ERROR);
            }

    }

}
