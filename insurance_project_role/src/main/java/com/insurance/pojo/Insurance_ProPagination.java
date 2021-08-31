package com.insurance.pojo;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Api("分页")
public class Insurance_ProPagination implements Serializable {

    private  String userCode;//用户账号
    private  Integer orgCode;//占位
    private  Integer pageSize;//每页记录数
    private  Integer currentPage;//当前页码
    private  String  searchType;//solr搜索类型
    private  String  searchContent;//solr搜索内容
}
