package com.insurance.service;

import com.insurance.pojo.Insurance_Pay;

import java.util.Map;

public interface Insurance_PayService {

    Insurance_Pay searchProDetails(String orderNo);

    Integer update_ProPayStatus(Map<String,Object> param);

    Integer update_ProEffectDate(Map<String,Object> param);
}
