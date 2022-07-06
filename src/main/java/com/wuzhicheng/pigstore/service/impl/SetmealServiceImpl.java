package com.wuzhicheng.pigstore.service.impl;

import com.wuzhicheng.pigstore.entity.Setmeal;
import com.wuzhicheng.pigstore.mapper.SetmealMapper;
import com.wuzhicheng.pigstore.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuzhicheng
 * @create 2022-07-04 18:13
 */
@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    SetmealMapper setmealMapper;
    @Override
    public List<Setmeal> selectByCid(Long cid) {
        return setmealMapper.selectByCid(cid);
    }
}
