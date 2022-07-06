package com.wuzhicheng.pigstore.controller;

import com.wuzhicheng.pigstore.service.TestService;
import com.wuzhicheng.pigstore.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuzhicheng
 * @create 2022-07-01 0:43
 */
@RestController
public class TestController {
    @Autowired
    TestService testService;
    @GetMapping("/ta")
    public void select(){
        testService.selectAll();
    }
}
