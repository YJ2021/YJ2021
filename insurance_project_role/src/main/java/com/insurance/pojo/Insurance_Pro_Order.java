package com.insurance.pojo;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;
import java.math.BigDecimal;

@Api("保险产品详情实体类")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SolrDocument(solrCoreName = "insurance")
public class Insurance_Pro_Order implements Serializable {
    @Field("id")
    private  Integer  id;//主键
    @Field("orderNo")
    private  String   orderNo;//订单编号
    @Field("proId")
    private  Integer  proId ;//保险编号
    @Field("proName")
    private  String   proName;//保险名称
    @Field("proType")
    private  String   proType ;//险种
    @Field("details")
    private  String   details ;//保险介绍（保存附文本）
    @Field("buyCondition")
    private  String   buyCondition ;//购买条件
    @Field("payAmount")
    private String payAmount ;//支付金额
    @Field("endDate")
    private  String   endDate ;//保险时间
    @Field("userCode")
    private  String   userCode ;//投保人用户名
    @Field("userName")
    private  String   userName ;//投保人真实姓名
    @Field("mail")
    private  String   mail ;//投保人邮箱
    @Field("idNumber")
    private  String   idNumber ;//投保人身份证号
    @Field("proBName")
    private  String   proBName ;//被保险人姓名
    @Field("proBidNumber")
    private  String   proBidNumber ;//被保险人身份证
    @Field("proRName")
    private  String   proRName ;//受益人姓名
    @Field("proRidNumber")
    private  String   proRidNumber;//受益人身份证
    @Field("applyDate")
    private  String   applyDate ;//申请时间
    @Field("approverDate")
    private  String   approverDate ;//批准时间
    @Field("effectDate")
    private  String   effectDate ;//生效时间
    //订单状态  0.申请中 1.初审完成 2.初审失败 3.复审失败 4.待支付 5.支付成功
    // 6.生效 7.过期 8.申请赔付中 9.赔付失败 10.已赔付
    @Field("statusType")
    private  String   statusType ;//状态
}
