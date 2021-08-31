package com.insurance.service.impl;

import com.insurance.mapper.Insurance_Role_MenuMapper;
import com.insurance.service.Insurance_Role_MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class Insurance_Role_MenuServiceImpl implements Insurance_Role_MenuService {
    @Autowired
    private Insurance_Role_MenuMapper role_menuMapper;

    @Override
    public List<Integer> searchMenuId(List<Integer> roleId) {
        return role_menuMapper.searchMenuId(roleId);
    }
}
