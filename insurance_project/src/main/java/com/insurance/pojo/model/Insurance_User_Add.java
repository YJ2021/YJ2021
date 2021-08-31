package com.insurance.pojo.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户实体类（注册使用）
 */
@Api("用户实体类（注册使用）")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Insurance_User_Add implements Serializable {
    @ApiModelProperty("手机号或微信号")
    private String  userCode;
    @ApiModelProperty("密码")
    private String  userPassword;
    @ApiModelProperty("真实姓名")
    private String userName;
    @ApiModelProperty("邮箱")
    private String mail;
    @ApiModelProperty("身份证号")
    private String idNumber;

}
