package com.insurance.mapper;

import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface Insurance_UserMapper {
    @ApiModelProperty("查询用户")
    Integer searchUser(String userCode);

}
