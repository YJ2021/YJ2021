package com.insurance.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 */
@Api("用户实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Insurance_User implements Serializable {
    @ApiModelProperty("主键")
    private Integer  id ;
    @ApiModelProperty("若是管理员分配，系统将自动生成唯一账号；自注册用户则为者手机号")
    private String userCode;
    @ApiModelProperty("若是管理员分配，系统将自动生成唯一账号；自注册用户则为自定义密码")
    private String userPassword;
    @ApiModelProperty("用户类型（标识：0 管理员 1自注册用户  2 保险销售部门 3 风险合规部）")
    private Integer userType;
    @ApiModelProperty("用户真实姓名")
    private String userName;
    @ApiModelProperty("邮箱")
    private String mail;
    @ApiModelProperty("身份证号")
    private String idNumber;
    @ApiModelProperty("创建时间")
    private Date creationDate;
    @ApiModelProperty("创建人")
    private String createdBy;
    @ApiModelProperty("修改时间")
    private Date modifyDate;
    @ApiModelProperty("修改人")
    private String modifiedBy;
    @ApiModelProperty("是否激活,(0 false，1 true,默认是0)")
    private Integer activated;

}
