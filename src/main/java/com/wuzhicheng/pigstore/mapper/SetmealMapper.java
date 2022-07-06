package com.wuzhicheng.pigstore.mapper;

import com.wuzhicheng.pigstore.entity.Dish;
import com.wuzhicheng.pigstore.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wuzhicheng
 * @create 2022-07-04 18:09
 */
@Mapper
public interface SetmealMapper {
    List<Setmeal> selectByCid(Long cid);
}
