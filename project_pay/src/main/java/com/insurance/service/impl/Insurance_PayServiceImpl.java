package com.insurance.service.impl;

import com.insurance.mapper.Insurance_PayMapper;
import com.insurance.pojo.Insurance_Pay;
import com.insurance.service.Insurance_PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
@Transactional
public class Insurance_PayServiceImpl implements Insurance_PayService {

    @Autowired
    Insurance_PayMapper payMapper;
    @Override
    public Insurance_Pay searchProDetails(String orderNo) {
        return payMapper.searchProDetails(orderNo);
    }

    @Override
    public Integer update_ProPayStatus(Map<String,Object> param) {
        return payMapper.update_ProPayStatus(param);
    }

    @Override
    public Integer update_ProEffectDate(Map<String, Object> param) {
        return payMapper.update_ProEffectDate(param);
    }
}
