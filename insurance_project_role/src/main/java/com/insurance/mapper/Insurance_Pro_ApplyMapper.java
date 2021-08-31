package com.insurance.mapper;

import com.insurance.pojo.Insurance_Pro_Apply;
import com.insurance.pojo.Insurance_Pro_Order;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Insurance_Pro_ApplyMapper {

    @ApiOperation("添加申请信息到审核表")
    Integer saveProApply(Insurance_Pro_Apply pro_apply);

    @ApiOperation("查询个人订单表")
    List<Insurance_Pro_Order> searchOrderList (@Param("userCode")String userCode, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    @ApiOperation("个人订单总页数查询")
    Integer searchMaxRows(String userCode);

    @ApiOperation("查询审核表")
    List<Insurance_Pro_Order> searchProApply(@Param("statusType")Integer[] statusType,@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    @ApiOperation("审核总页数查询")
    Integer searchApplyMaxRows(Integer[] statusType);

    @ApiOperation("修改订单表审核状态")
    Integer updateApplyStatus(Insurance_Pro_Order pro_orderStatus);

    @ApiOperation("修改审核表订单状态")
    Integer update_ProApplyStatus(Insurance_Pro_Apply pro_applyStatus);

}
