package com.wuzhicheng.pigstore.service;

import com.wuzhicheng.pigstore.entity.Setmeal;

import java.util.List;

/**
 * @author wuzhicheng
 * @create 2022-07-04 18:11
 */
public interface SetmealService {
    List<Setmeal> selectByCid(Long cid);
}
