package com.insurance.pojo;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Api("角色菜单中间表")
public class Insurance_Role_Menu implements Serializable {

    private Integer roleId;
    private Integer menuId;
}
