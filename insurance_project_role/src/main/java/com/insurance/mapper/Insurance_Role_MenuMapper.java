package com.insurance.mapper;


import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface Insurance_Role_MenuMapper {

    @ApiModelProperty("查询菜单列表Id")
    List<Integer> searchMenuId(List<Integer> roleId);
}
