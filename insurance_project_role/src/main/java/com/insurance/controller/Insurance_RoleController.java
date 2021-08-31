package com.insurance.controller;

import com.insurance.pojo.DTO;
import com.insurance.pojo.Insurance_Menu;
import com.insurance.util.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/roleController")
@Slf4j
public class Insurance_RoleController {
    @Autowired
    private ServiceUtil serviceUtil;
    @RequestMapping(value = "/searchMenu/{userCode}")
    @ApiOperation("通过用户名获取用户菜单权限")
    public DTO searchMenu(HttpServletRequest request, HttpServletResponse response, @PathVariable String userCode){
        log.info("userCode:"+userCode);
        //判断传入参数是否为空
        if(EmptyUtils.isNotEmpty(userCode)){
               //获取用户对象
            Integer userId = serviceUtil.searchUser(userCode);
               //判断user对象是否为空
               if(EmptyUtils.isNotEmpty(userId)){
                   //获取角色ID
                   List<Integer> roleIdList = serviceUtil.searchRoleId(userId);
                   //判断角色ID是否为空
                   if(EmptyUtils.isNotEmpty(roleIdList)){
                       List<Integer> menuIdList = serviceUtil.searchMenuId(roleIdList);
                       //判断菜单ID是否为空
                       if(EmptyUtils.isNotEmpty(menuIdList)){
                           //获取菜单列表
                           List<Insurance_Menu> menuContents = serviceUtil.searchMenuContent(menuIdList);
                           if(EmptyUtils.isNotEmpty(menuContents)){
                               List<MenuUtil> menu=new ArrayList<>();
                               //遍历菜单集合
                               for(int i=0;i<menuContents.size();i++){
                                   //获取根节点（一级菜单）
                                   MenuUtil menuUtil1=new MenuUtil();
                                   if(menuContents.get(i).getPId().equals("0")){
                                       menuUtil1.setIndex(menuContents.get(i).getId()+"");
                                       menuUtil1.setMenuContent(menuContents.get(i).getMenuContent());
                                       menuUtil1.setPath(menuContents.get(i).getUrl());
                                       List<MenuUtil> menuList=new ArrayList<>();
                                       for(int j=0;j<menuContents.size();j++){
                                           //获取二级菜单
                                           if(!menuContents.get(j).getPId().equals("0")){
                                               MenuUtil menuUtil2=new MenuUtil();
                                               menuUtil2.setIndex(menuContents.get(j).getId()+"");
                                               menuUtil2.setMenuContent(menuContents.get(j).getMenuContent());
                                               menuUtil2.setPath(menuContents.get(j).getUrl());
                                               menuList.add(menuUtil2);
                                           }
                                       }
                                       menuUtil1.setNodes(menuList);//将子节点放入父节点中
                                       menu.add(menuUtil1);//将菜单放入集合
                                   }

                               }
                               return DTOUtil.returnSucess("查询菜单列表成功",menu);
                           }
                           else{
                               return DTOUtil.returnFalse("菜单列表为空", ErrorCode.ROLE_OTHER);
                           }
                       }
                       else {
                           return DTOUtil.returnFalse("用户未分配菜单权限",ErrorCode.ROLE_USER_NOT_MENUS);
                       }
                   }
                   else{
                       return DTOUtil.returnFalse("用户未分配角色",ErrorCode.ROLE_USER_NOT_ROLES);
                   }
               }
               else {
                   return DTOUtil.returnFalse("用户不存在",ErrorCode.ROLE_USER_NOT_EXIST);
               }
           }
        else{
            return DTOUtil.returnFalse("传入参数为空",ErrorCode.ROLE_INPUT_EMPTY);
        }
    }
}
