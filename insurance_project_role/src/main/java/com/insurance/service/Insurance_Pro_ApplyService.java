package com.insurance.service;

import com.insurance.pojo.Insurance_Pro_Apply;
import com.insurance.pojo.Insurance_Pro_Order;

import java.util.List;


public interface Insurance_Pro_ApplyService {

    Integer saveProApply(Insurance_Pro_Apply pro_apply);

    List<Insurance_Pro_Order> searchOrderList(String userCode, Integer currentPage, Integer pageSize);

    List<Insurance_Pro_Order> searchProApply(Integer[] statusType, Integer currentPage, Integer pageSize);

    Integer searchMaxRows(String userCode);

    Integer searchApplyMaxRows(Integer[] statusType);

    Integer updateApplyStatus(Insurance_Pro_Order pro_orderStatus);

    Integer update_ProApplyStatus(Insurance_Pro_Apply pro_applyStatus);
}
