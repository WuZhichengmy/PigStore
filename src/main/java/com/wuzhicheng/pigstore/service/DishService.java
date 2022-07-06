package com.wuzhicheng.pigstore.service;

import com.wuzhicheng.pigstore.dto.DishDto;
import com.wuzhicheng.pigstore.entity.Dish;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuzhicheng
 * @create 2022-07-04 18:11
 */
public interface DishService {
    List<Dish> selectByCid(Long cid);
    //新增菜品，并插入菜品对应的口味数据
    public void saveWithFlavor(DishDto dishDto, Long nowId);
    List<Dish> selectByPage(@Param("begin") int begin,
                            @Param("end") int end,
                            @Param("name") String name);
    int getLength();
    //根据菜品id查菜品信息和口味信息
    DishDto getByIdWithFlavor(Long id);
    Dish getById(Long id);
    void updateWithFlavor(DishDto dishDto, Long nowId);
}
