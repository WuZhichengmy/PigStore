package com.wuzhicheng.pigstore.service;

import com.wuzhicheng.pigstore.entity.DishFlavor;

import java.util.List;

/**
 * @author wuzhicheng
 * @create 2022-07-05 22:39
 */
public interface DishFlavorService {
    int insert(DishFlavor record);
    List<DishFlavor> getByDid(Long did);
    int removeByDid(Long did);
}
