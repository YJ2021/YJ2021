package com.insurance.util;

import com.insurance.pojo.Insurance_Menu;
import com.insurance.pojo.Insurance_User;
import com.insurance.service.Insurance_MenuService;
import com.insurance.service.Insurance_Role_MenuService;
import com.insurance.service.Insurance_UserService;
import com.insurance.service.Insurance_User_RoleService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class ServiceUtil {

    @Autowired
    private Insurance_UserService userService;

    @Autowired
    private Insurance_User_RoleService user_roleService;

    @Autowired
    private Insurance_Role_MenuService role_menuService;

    @Autowired
    private Insurance_MenuService menuService;

    public Integer searchUser(String userCode){
        return  userService.searchUser(userCode);
    }

    public List<Integer> searchRoleId(Integer userId){
        return  user_roleService.searchRoleId(userId);
    }

    public List<Integer> searchMenuId(List<Integer> roleId){
        return  role_menuService.searchMenuId(roleId);
    }

    public List<Insurance_Menu> searchMenuContent(List<Integer> menuId){
        return  menuService.searchMenuContent(menuId);
    }
}
