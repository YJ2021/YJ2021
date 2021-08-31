package com.insurance.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Api("激活验证")
public class Validate implements Serializable {
    @ApiModelProperty("用户名")
    private  String userCode;
    @ApiModelProperty("验证码")
    private  String code;

}
