package com.insurance.service.user.impl;

import com.insurance.mapper.user.User_RegisterMapper;
import com.insurance.pojo.Forget;
import com.insurance.pojo.Insurance_User;
import com.insurance.service.user.User_RegisterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class User_RegisterServiceImpl implements User_RegisterService {
    @Autowired
    User_RegisterMapper userRegisterMapper;


    @Override
    @ApiOperation("查询用户是否存在")
    public Insurance_User searchUser(String userCode) {
        return userRegisterMapper.searchUser(userCode);
    }

    @Override
    @ApiOperation("修改用户状态")
    public Integer updateActivated(String userCode) {
        return userRegisterMapper.updateActivated(userCode);
    }

    @Override
    @ApiOperation("登录")
    public Insurance_User login(Insurance_User user) {
        return userRegisterMapper.login(user);
    }

    @Override
    public Integer initializePassword(Forget user) {
        return userRegisterMapper.initializePassword(user);
    }

    @Override
    public Integer updateUser(Insurance_User user) {
        return userRegisterMapper.updateUser(user);
    }

    @ApiOperation("用户注册")
    public Integer insertUser(Insurance_User user) {
        Integer rows=userRegisterMapper.insertUser(user);
        return rows;
    }
}
