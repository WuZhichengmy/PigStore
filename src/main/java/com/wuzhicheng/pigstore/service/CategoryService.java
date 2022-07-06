package com.wuzhicheng.pigstore.service;

import com.wuzhicheng.pigstore.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuzhicheng
 * @create 2022-07-04 13:09
 */
public interface CategoryService {
    int save(Category category);
    List<Category> selectByPage(int begin, int end);
    int getLength();
    int removeById(Long id);
    int updateById(Category category);
    List<Category> selectByType(Category category);
    Category getById(Long id);
}
