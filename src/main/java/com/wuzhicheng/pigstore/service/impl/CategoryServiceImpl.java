package com.wuzhicheng.pigstore.service.impl;

import com.wuzhicheng.pigstore.common.CustomException;
import com.wuzhicheng.pigstore.entity.Category;
import com.wuzhicheng.pigstore.entity.Dish;
import com.wuzhicheng.pigstore.entity.Setmeal;
import com.wuzhicheng.pigstore.mapper.CategoryMapper;
import com.wuzhicheng.pigstore.service.CategoryService;
import com.wuzhicheng.pigstore.service.DishService;
import com.wuzhicheng.pigstore.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuzhicheng
 * @create 2022-07-04 13:09
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    DishService dishService;

    @Autowired
    SetmealService setmealService;

    @Override
    public int save(Category category) {
        return categoryMapper.save(category);
    }

    @Override
    public List<Category> selectByPage(int begin, int end) {
        return categoryMapper.selectByPage(begin,end);
    }

    @Override
    public int getLength() {
        return categoryMapper.getLength();
    }

    /**
     * 根据id删除分类，并且在删除之前判断是否有关联的菜品或者套餐
     * @param id
     * @return
     */
    @Override
    public int removeById(Long id) {
        //查询是否关联菜品
        List<Dish> dishes = dishService.selectByCid(id);
        if(dishes.size()!=0){
            throw new CustomException("当前分类下关联了菜品");
        }
        //查询是否关联套餐
        List<Setmeal> setmeals = setmealService.selectByCid(id);
        if(setmeals.size()!=0){
            throw new CustomException("当前分类下关联了套餐");
        }
        return categoryMapper.removeById(id);
    }

    /**
     * 根据id来修改分类信息
     * @param category
     * @return
     */
    @Override
    public int updateById(Category category) {
        return categoryMapper.updateById(category);
    }

    @Override
    public List<Category> selectByType(Category category) {
        return categoryMapper.selectByType(category);
    }

    @Override
    public Category getById(Long id) {
        return categoryMapper.getById(id);
    }
}
