package com.insurance.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Api("菜单实体类")
public class Insurance_Menu implements Serializable {
    @ApiModelProperty("主键")
    private Integer id;
    @ApiModelProperty("父级Id ")
    private String pId;
    @ApiModelProperty("菜单内容")
    private String menuContent;
    @ApiModelProperty("菜单对应页面地址")
    private String url;
}
