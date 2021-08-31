package com.insurance.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Api("订单审核实体类")
public class Insurance_Pro_Apply implements Serializable {

    @ApiModelProperty("主键")
    private  String id;
    @ApiModelProperty("订单编号")
    private String orderNo;
    @ApiModelProperty("订单状态")//状态（0代表客户申请 1初审同意 2初审不同意 3代表复审同意 4复审不同意）
    private String proStatus;
    //临时属性
    //private String proStatusType;
}
