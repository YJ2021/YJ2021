package com.insurance.service.impl;

import com.insurance.mapper.Insurance_ProMapper;
import com.insurance.pojo.Insurance_Pro_Order;
import com.insurance.pojo.Insurance_Products;
import com.insurance.pojo.Insurance_User;
import com.insurance.service.Insurance_ProService;
import io.swagger.annotations.ApiOperation;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Component
@Transactional
public class Insurance_ProServiceImpl implements Insurance_ProService {

    @Autowired
    private Insurance_ProMapper proMapper;

    @Autowired
    private SolrClient solrClient;

    @Override
    public List<Insurance_Products> searchPro(Integer currentPage, Integer pageSize) {
        return proMapper.searchPro(currentPage,pageSize);
    }

    @Override
    public Integer searchProMaxRows() {
        return proMapper.searchProMaxRows();
    }

    @Override
    public Insurance_Products searchProDetails(String id){
        return proMapper.searchProDetails(id);
    }

    @Override
    public Insurance_User searchUser(String userCode) {
        return proMapper.searchUser(userCode);
    }

    @Override
    public Integer saveOrder(Insurance_Pro_Order order) {
        return proMapper.saveOrder(order);
    }

    @Override
    public List<Insurance_Pro_Order> searchProBySolr(Integer currentPage, Integer pageSize, String field, String parm) {
        //创建solr查询对象
        SolrQuery solrQuery=new SolrQuery();
        //设置查询所有值
        solrQuery.set("q","*:*");
        //设置排序
        solrQuery.setSort("id",SolrQuery.ORDER.desc);
        //设置分页显示
        solrQuery.setStart((currentPage-1)*pageSize);
        solrQuery.setRows(pageSize);
        //条件查询
        //指定字段查询
        if(parm!=null&&!parm.equals("")){
            solrQuery.set("q",""+field+":"+parm+"");
        }
        try {
            //获取查询结果集
            QueryResponse queryResponse = solrClient.query(solrQuery);
            //将查询结果集转换成对应实体对象
            List<Insurance_Pro_Order> proOrders = queryResponse.getBeans(Insurance_Pro_Order.class);
            for(Insurance_Pro_Order a:proOrders){
                System.out.println(a.toString());
            }
            return proOrders;
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @ApiOperation("查询保险产品solr")
    public List<Insurance_Pro_Order> searchAllProBySolr(Integer currentPage, Integer pageSize, String field, String parm) {
        //创建solr查询对象
        SolrQuery solrQuery=new SolrQuery();
        //设置查询所有值
        solrQuery.set("q","*:*");
        //设置排序
        solrQuery.setSort("id",SolrQuery.ORDER.desc);
        //设置分页显示
        solrQuery.setStart((currentPage-1)*pageSize);
        solrQuery.setRows(pageSize);
        //条件查询
        //指定字段查询
        if(parm!=null&&!parm.equals("")){
            solrQuery.set("q",""+field+":"+parm+"");
        }
        try {
            //获取查询结果集
            QueryResponse queryResponse = solrClient.query(solrQuery);
            //将查询结果集转换成对应实体对象
            List<Insurance_Pro_Order> proOrders = queryResponse.getBeans(Insurance_Pro_Order.class);
            for(Insurance_Pro_Order a:proOrders){
                System.out.println(a.toString());
            }
            return proOrders;
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    ;
}
