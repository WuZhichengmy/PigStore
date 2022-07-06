package com.wuzhicheng.pigstore.service.impl;

import com.wuzhicheng.pigstore.entity.DishFlavor;
import com.wuzhicheng.pigstore.mapper.DishFlavorMapper;
import com.wuzhicheng.pigstore.service.DishFlavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuzhicheng
 * @create 2022-07-05 22:40
 */
@Service
public class DishFlavorServiceImpl implements DishFlavorService {
    @Autowired
    DishFlavorMapper dishFlavorMapper;
    @Override
    public int insert(DishFlavor record) {

        return dishFlavorMapper.insert(record);
    }

    @Override
    public List<DishFlavor> getByDid(Long did) {
        return dishFlavorMapper.getByDid(did);
    }

    @Override
    public int removeByDid(Long did) {
        return dishFlavorMapper.removeByDid(did);
    }
}
