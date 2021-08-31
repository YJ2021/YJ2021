package com.insurance.service;
import com.insurance.pojo.Insurance_Pro_Order;
import com.insurance.pojo.Insurance_Products;
import com.insurance.pojo.Insurance_User;

import java.util.List;

public interface Insurance_ProService {

     List<Insurance_Products> searchPro(Integer currentPage, Integer pageSize);

     Integer searchProMaxRows();

     Insurance_Products searchProDetails(String id);

     Insurance_User searchUser(String userCode);

     Integer saveOrder(Insurance_Pro_Order order);

     List<Insurance_Pro_Order> searchProBySolr(Integer currentPage, Integer pageSize,String field,String parm);

     List<Insurance_Pro_Order> searchAllProBySolr(Integer currentPage, Integer pageSize,String field,String parm);
}
