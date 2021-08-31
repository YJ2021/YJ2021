package com.insurance.service.impl;

import com.insurance.mapper.Insurance_User_RoleMapper;
import com.insurance.service.Insurance_User_RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class Insurance_User_RoleServiceImpl implements Insurance_User_RoleService {
    @Autowired
    private Insurance_User_RoleMapper user_roleMapper;

    @Override
    public List<Integer> searchRoleId(Integer userId) {
        return user_roleMapper.searchRoleId(userId);
    }
}
