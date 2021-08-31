package com.insurance.service.impl;

import com.insurance.mapper.Insurance_UserMapper;
import com.insurance.service.Insurance_UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class Insurance_UserServiceImpl implements Insurance_UserService {
    @Autowired
    private Insurance_UserMapper userMapper;

    @Override
    public Integer searchUser(String userCode) {
        return userMapper.searchUser(userCode);
    }
}
