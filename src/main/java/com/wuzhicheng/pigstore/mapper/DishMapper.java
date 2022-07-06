package com.wuzhicheng.pigstore.mapper;

import com.wuzhicheng.pigstore.entity.Dish;
import com.wuzhicheng.pigstore.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuzhicheng
 * @create 2022-07-04 18:09
 */
@Mapper
public interface DishMapper {
    List<Dish> selectByCid(Long cid);
    int insert(Dish dish);
    List<Dish> selectByPage(@Param("begin") int begin,
                            @Param("end") int end,
                            @Param("name") String name);
    int getLength();
    Dish getById(Long id);
    int updateById(Dish dish);
}
