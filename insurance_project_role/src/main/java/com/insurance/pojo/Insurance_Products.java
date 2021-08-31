package com.insurance.pojo;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Api("保险产品实体类")
public class Insurance_Products implements Serializable {

    private Integer  id;//'主键',
    private String proName ;// '保险名称',
    private String proType ;// '险种',
    private String details ;// '保险介绍（保存附文本）',
    private String buyCondition ;//'购买条件',
    private BigDecimal payAmount ;//'支付金额',
    private String endDate ;//'保险时间',
    private Date creationDate ;// '发布时间'
    private String url;//路径
    //临时属性
    private String creDate;//前台展示发布时间
}
