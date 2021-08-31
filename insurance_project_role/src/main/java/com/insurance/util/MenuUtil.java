package com.insurance.util;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Api("前台菜单返回数据封装")
public class MenuUtil implements Serializable {

    private String index;//菜单等级标识
    private String menuContent;//菜单等级标识
    private String path;//菜单访问路径
    private List<MenuUtil> nodes;//子菜单

}
