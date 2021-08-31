package com.insurance.mapper;

import com.insurance.pojo.Insurance_Menu;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Insurance_MenuMapper {

    @ApiModelProperty("查询菜单列表")
    List<Insurance_Menu> searchMenuContent(List<Integer> menuId);
}
