package com.insurance.controller.user;

import com.insurance.pojo.DTO;
import com.insurance.pojo.Insurance_User;
import com.insurance.pojo.Validate;
import com.insurance.pojo.model.Insurance_User_Add;
import com.insurance.service.user.User_RegisterService;
import com.insurance.util.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class User_RegisterController {
    //private final Logger log= LoggerFactory.getLogger(User_RegisterController.class);
    @Autowired
    User_RegisterService userRegisterService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @ApiOperation("注册")
    @RequestMapping(value = "/register")
    public DTO userRegister(HttpServletRequest request, HttpServletResponse response, @RequestBody Insurance_User_Add user_add){
          log.info("进入注册 controller"+user_add.toString());
        Insurance_User oldUser =userRegisterService.searchUser(user_add.getUserCode());
        if (oldUser!=null){
            return DTOUtil.returnFalse("注册失败,用户已存在", ErrorCode.AUTH_USER_ALREADY_EXISTS);
        }
          Insurance_User user=new Insurance_User();
         user.setUserCode(user_add.getUserCode());
        //密码加密
         String md5passwd= MD5Util.getMd5(user_add.getUserPassword(),32);
         user.setUserPassword(md5passwd);
         user.setUserType(1);
         user.setUserName(user_add.getUserName());
         user.setMail(user_add.getMail());
         user.setIdNumber(user_add.getIdNumber());
         user.setCreatedBy(user_add.getUserName());
         user.setCreationDate(new Date());
         user.setActivated(0);
         //注册用户存储
         Integer rows=userRegisterService.insertUser(user);
         if(rows>0){
             String code=SMSUtil.testcheck(user.getUserCode());
             stringRedisTemplate.opsForValue().set(user.getUserCode(),code,2);
              return DTOUtil.returnSucess("注册成功");
         }else {
             return DTOUtil.returnFalse("注册失败", ErrorCode.AUTH_UNKNOWN);
         }
    }

    @ApiOperation("激活验证")
    @RequestMapping(value = "/validate")
    public DTO validate(@RequestBody Validate validate){
        try {
            log.info("进入注册 controller"+validate.toString());
            //判断传入参数是否为空
            if(EmptyUtils.isNotEmpty(validate.getUserCode())&&EmptyUtils.isNotEmpty(validate.getCode())){
               //判断数据库中是否存在此用户
                Insurance_User user = userRegisterService.searchUser(validate.getUserCode());
                if(user!=null){
                  //判断用户状态
                    if(user.getActivated()==0){
                        String s = stringRedisTemplate.opsForValue().get(validate.getUserCode());
                        //判断redis中是否存在
                        if(EmptyUtils.isNotEmpty(s.trim())){
                          //验证
                            if(s.trim().equals(validate.getCode())){
                                //修改用户状态
                                userRegisterService.updateActivated(validate.getUserCode());
                                //清除验证码
                                stringRedisTemplate.delete(validate.getUserCode());
                                return DTOUtil.returnSucess("验证成功");
                            }else{
                                return DTOUtil.returnFalse("激活失败，验证码错误",ErrorCode.AUTH_CODE_ERROR);
                            }
                        }
                        else{
                            String code=SMSUtil.testcheck(user.getUserCode());
                            stringRedisTemplate.opsForValue().set(user.getUserCode(),code);
                            return DTOUtil.returnFalse("激活失败，验证码已失效，请重新输入验证码",ErrorCode.AUTH_CODE_HAS_EXPIRED);
                        }
                    }else{
                        return DTOUtil.returnFalse("激活失败，用户已激活，请登录",ErrorCode.AUTH_ALREADY_ACTIVATED);
                    }
                }
                else{
                    return DTOUtil.returnFalse("激活失败，用户不存在",ErrorCode.AUTH_USER_NOT_EXIST);
                }
            }
            else{
                return DTOUtil.returnFalse("激活失败，传入参数为空",ErrorCode.AUTH_PARAMETER_IS_EMPTY);
            }
        } catch (Exception e) {
            return DTOUtil.returnFalse("激活失败,后台代码错误", ErrorCode.AUTH_BACKGROUND_CODE_ERROR);
        }
    }

}
