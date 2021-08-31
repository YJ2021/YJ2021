package com.insurance.mapper.user;

import com.insurance.pojo.Forget;
import com.insurance.pojo.Insurance_User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@Api("用户注册mapper")
public interface User_RegisterMapper {
    @ApiOperation("用户注册")
    Integer insertUser(Insurance_User user);
    @ApiOperation("查询用户是否存在")
    Insurance_User searchUser(String userCode);
    @ApiOperation("修改用户状态")
    Integer updateActivated(String userCode);
    @ApiOperation("登录")
    Insurance_User login(Insurance_User user);
    @ApiModelProperty("初始化密码")
    Integer initializePassword(Forget user);
    @ApiModelProperty("修改用户信息")
    Integer updateUser(Insurance_User user);
}
