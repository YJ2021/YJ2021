package com.insurance.pojo;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Api("用户角色中间表")
public class Insurance_User_Role  implements Serializable {

    private Integer userId;
    private Integer roleId;
}
