package com.insurance.util;

import com.insurance.mapper.Insurance_MenuMapper;
import com.insurance.mapper.Insurance_Role_MenuMapper;
import com.insurance.mapper.Insurance_UserMapper;
import com.insurance.mapper.Insurance_User_RoleMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class MapperUtil {

    @Autowired
    private Insurance_UserMapper userMapper;

    @Autowired
    private Insurance_User_RoleMapper user_roleMapper;

    @Autowired
    private Insurance_Role_MenuMapper role_menuMapper;

    @Autowired
    private Insurance_MenuMapper menuMapper;
}
