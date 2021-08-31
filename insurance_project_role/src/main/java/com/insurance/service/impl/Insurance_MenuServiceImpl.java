package com.insurance.service.impl;

import com.insurance.mapper.Insurance_MenuMapper;
import com.insurance.pojo.Insurance_Menu;
import com.insurance.service.Insurance_MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class Insurance_MenuServiceImpl implements Insurance_MenuService {
    @Autowired
    private Insurance_MenuMapper menuMapper;

    @Override
    public List<Insurance_Menu> searchMenuContent(List<Integer> menuId) {
        return menuMapper.searchMenuContent(menuId);
    }
}
