package com.insurance.mapper;

import com.insurance.pojo.Insurance_Pay;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface Insurance_PayMapper {

    @ApiOperation("根据订单号查询订单信息")
    Insurance_Pay searchProDetails(String orderNo);

    @ApiOperation("修改订单状态")
    Integer update_ProPayStatus(Map<String,Object> param);

    @ApiOperation("订单生效时间")
    Integer update_ProEffectDate(Map<String,Object> param);
}
