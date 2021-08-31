package com.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubbo.service.TestService;
import org.springframework.stereotype.Component;

@Service(version = "1.0.0" ,timeout = 3000)
@Component
public class TestServiceImpl implements TestService {
    @Override
    public void testDubbo() {
        System.out.println("Test Dubbo");
    }
}
