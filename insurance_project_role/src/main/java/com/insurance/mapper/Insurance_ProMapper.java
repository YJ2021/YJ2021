package com.insurance.mapper;


import com.insurance.pojo.Insurance_Pro_Order;
import com.insurance.pojo.Insurance_Products;
import com.insurance.pojo.Insurance_User;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Insurance_ProMapper {

       @ApiOperation("查询保险产品")
       List<Insurance_Products> searchPro(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

       @ApiOperation("查询保险总记录数")
       Integer searchProMaxRows();

       @ApiOperation("查询保险产品详情")
       Insurance_Products searchProDetails(String id);

       @ApiOperation("查询用户信息")
       Insurance_User searchUser(String userCode);

       @ApiOperation("保存申请订单信息")
       Integer saveOrder(Insurance_Pro_Order order);


}
