package com.insurance.service.impl;

import com.insurance.mapper.Insurance_Pro_ApplyMapper;
import com.insurance.pojo.Insurance_Pro_Apply;
import com.insurance.pojo.Insurance_Pro_Order;
import com.insurance.service.Insurance_Pro_ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class Insurance_Pro_ApplyServiceImpl implements Insurance_Pro_ApplyService {

    @Autowired
    Insurance_Pro_ApplyMapper pro_applyMapper;

    @Override
    public Integer saveProApply(Insurance_Pro_Apply pro_apply) {
        return pro_applyMapper.saveProApply(pro_apply);
    }

    @Override
    public List<Insurance_Pro_Order> searchOrderList(String userCode, Integer currentPage, Integer pageSize) {
        return pro_applyMapper.searchOrderList(userCode,currentPage,pageSize);
    }

    @Override
    public List<Insurance_Pro_Order> searchProApply(Integer[] statusType, Integer currentPage, Integer pageSize) {
        System.out.println("进入初审service...");
        return pro_applyMapper.searchProApply(statusType,currentPage,pageSize);
    }

    @Override
    public Integer searchMaxRows(String userCode) {
        return pro_applyMapper.searchMaxRows(userCode);
    }

    @Override
    public Integer searchApplyMaxRows(Integer[] statusType) {
        return pro_applyMapper.searchApplyMaxRows(statusType);
    }

    @Override
    public Integer updateApplyStatus(Insurance_Pro_Order pro_orderStatus) {
        return pro_applyMapper.updateApplyStatus(pro_orderStatus);
    }

    @Override
    public Integer update_ProApplyStatus(Insurance_Pro_Apply pro_applyStatus) {
        return pro_applyMapper.update_ProApplyStatus(pro_applyStatus);
    }
}
