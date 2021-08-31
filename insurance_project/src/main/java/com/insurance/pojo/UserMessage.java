package com.insurance.pojo;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Api("返回前台用户信息的临时实体类")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserMessage implements Serializable {

    private Integer  id ;//主键
    private String userCode;//若是管理员分配，系统将自动生成唯一账号；自注册用户则为邮箱或者手机号
    private String userPassword; //若是管理员分配，系统将自动生成唯一账号；自注册用户则为自定义密码
    private String userType;//用户类型（标识：0 管理员 1自注册用户  2 保险销售部门 3 风险合规部
    private String userName;//用户真实姓名',
    private String mail;//微信号(手机或邮箱)
    private String idNumber;//身份证号
    private String creationDate;//创建时间
    private String createdBy;//创建人
    private String modifyDate;//修改时间
    private String modifiedBy;//修改人
    private String activated;//是否激活,(0 false，1 true,默认是0)
}
