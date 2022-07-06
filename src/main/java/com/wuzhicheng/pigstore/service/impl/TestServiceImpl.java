package com.wuzhicheng.pigstore.service.impl;

import com.wuzhicheng.pigstore.entity.Test;
import com.wuzhicheng.pigstore.mapper.TestMapper;
import com.wuzhicheng.pigstore.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuzhicheng
 * @create 2022-07-01 0:42
 */
@Service
public class TestServiceImpl implements TestService{
    @Autowired
    TestMapper testMapper;
    @Override
    public List<Test> selectAll() {
        return testMapper.selectAll();
    }
}
