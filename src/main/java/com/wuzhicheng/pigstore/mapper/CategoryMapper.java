package com.wuzhicheng.pigstore.mapper;

import com.wuzhicheng.pigstore.entity.Category;
import com.wuzhicheng.pigstore.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuzhicheng
 * @create 2022-07-04 12:53
 */
@Mapper
public interface CategoryMapper {
    int save(Category category);
    List<Category> selectByPage(@Param("begin") int begin,
                                @Param("end") int end);
    int getLength();
    int removeById(Long id);
    int updateById(Category category);
    List<Category> selectByType(Category category);
    Category getById(Long id);
}
