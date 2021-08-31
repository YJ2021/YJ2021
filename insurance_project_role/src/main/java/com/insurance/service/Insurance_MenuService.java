package com.insurance.service;

import com.insurance.pojo.Insurance_Menu;

import java.util.List;


public interface Insurance_MenuService {

    List<Insurance_Menu> searchMenuContent(List<Integer> menuId);
}
