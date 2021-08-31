package com.insurance.pojo;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Api("订单数据实体类")
public class Insurance_Pay implements Serializable {

    private  Integer  id;//主键
    private  String   orderNo;//订单编号
    private  Integer  proId ;//保险编号
    private  String   proName;//保险名称
    private  String   proType ;//险种
    private  String   details ;//保险介绍（保存附文本）
    private  String   buyCondition ;//购买条件
    private BigDecimal payAmount ;//支付金额
    private  String   endDate ;//保险时间
    private  String   userCode ;//投保人用户名
    private  String   userName ;//投保人真实姓名
    private  String   mail ;//投保人邮箱
    private  String   idNumber ;//投保人身份证号
    private  String   proBName ;//被保险人姓名
    private  String   proBidNumber ;//被保险人身份证
    private  String   proRName ;//受益人姓名
    private  String   proRidNumber;//受益人身份证
    private  String   applyDate ;//申请时间
    private  String   approverDate ;//批准时间
    private  String   effectDate ;//生效时间
    //订单状态  0.申请中 1.初审完成 2.初审失败 3.复审失败 4.待支付 5.支付成功
    // 6.生效 7.过期 8.申请赔付中 9.赔付失败 10.已赔付
    private  String   statusType ;//状态
}
