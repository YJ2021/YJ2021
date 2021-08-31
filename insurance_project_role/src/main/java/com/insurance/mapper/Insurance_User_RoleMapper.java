package com.insurance.mapper;


import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface Insurance_User_RoleMapper {

    @ApiModelProperty("查询角色Id")
    List<Integer> searchRoleId(Integer userId);
}
