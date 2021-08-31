package com.insurance.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@Api("忘记密码")
@AllArgsConstructor
@NoArgsConstructor
public class Forget implements Serializable {
    @ApiModelProperty("用户名")
    private String userCode;
    @ApiModelProperty("验证码")
    private String code;
    @ApiModelProperty("初始化密码")
    private String initializePassword;
}
