package com.wuzhicheng.pigstore.service.impl;

import com.wuzhicheng.pigstore.dto.DishDto;
import com.wuzhicheng.pigstore.entity.Dish;
import com.wuzhicheng.pigstore.entity.DishFlavor;
import com.wuzhicheng.pigstore.mapper.DishMapper;
import com.wuzhicheng.pigstore.service.DishFlavorService;
import com.wuzhicheng.pigstore.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 * @author wuzhicheng
 * @create 2022-07-04 18:12
 */
@Service
public class DishServiceImpl implements DishService {
    @Autowired
    DishMapper dishMapper;
    @Autowired
    DishFlavorService dishFlavorService;
    @Override
    public List<Dish> selectByCid(Long cid) {
        return dishMapper.selectByCid(cid);
    }

    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto, Long nowId) {
        Dish dish=new Dish();
        dish.setName(dishDto.getName());
        dish.setCategoryId(dishDto.getCategoryId());
        dish.setPrice(dishDto.getPrice());
        dish.setCode(dishDto.getCode());
        dish.setImage(dishDto.getImage());
        dish.setDescription(dishDto.getDescription());
        dish.setStatus(1);
        dish.setSort(dishDto.getSort());
        dish.setIsDeleted(0);
        dish.setSort(0);
        //设置时间
        dish.setCreateTime(LocalDateTime.now());
        dish.setUpdateTime(LocalDateTime.now());
        //获得当前登录用户的id
        dish.setCreateUser(nowId);
        dish.setUpdateUser(nowId);
        //设置id
        Random random=new Random();
        Integer number=random.nextInt(90000)+10000;
        String idStr=System.currentTimeMillis()+String.valueOf(number);
        Long id=Long.parseLong(idStr);
        dish.setId(id);
        //插入数据
        dishMapper.insert(dish);
        for(DishFlavor dishFlavor:dishDto.getFlavors()){
            //设置时间
            dishFlavor.setCreateTime(LocalDateTime.now());
            dishFlavor.setUpdateTime(LocalDateTime.now());
            //获得当前登录用户的id
            dishFlavor.setCreateUser(nowId);
            dishFlavor.setUpdateUser(nowId);
            //设置id
            random=new Random();
            number=random.nextInt(90000)+10000;
            idStr=System.currentTimeMillis()+String.valueOf(number);
            Long id1=Long.parseLong(idStr);
            dishFlavor.setId(id1);
            dishFlavor.setDishId(id);
            dishFlavor.setIsDeleted(0);
            dishFlavorService.insert(dishFlavor);
        }
    }

    @Override
    public List<Dish> selectByPage(int begin, int end, String name) {
        return dishMapper.selectByPage(begin,end,name);
    }

    @Override
    public int getLength() {
        return dishMapper.getLength();
    }

    @Override
    public DishDto getByIdWithFlavor(Long id) {
        //查询菜品基本信息
        Dish dish = this.getById(id);
        //查询菜品口味信息
        List<DishFlavor> flavors = dishFlavorService.getByDid(id);
        DishDto dishDto=new DishDto();
        BeanUtils.copyProperties(dish,dishDto);
        dishDto.setFlavors(flavors);
        return dishDto;
    }

    @Override
    public Dish getById(Long id) {
        return dishMapper.getById(id);
    }

    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto, Long nowId) {
        //更新dish表的基本信息
        //设置时间
        dishDto.setUpdateTime(LocalDateTime.now());
        //获得当前登录用户的id
        dishDto.setUpdateUser(nowId);
        System.out.println(dishDto);
        int i = dishMapper.updateById(dishDto);
        //清理当前菜品对应的口味数据
        dishFlavorService.removeByDid(dishDto.getId());
        //添加当前提交的口味数据
        List<DishFlavor> flavors = dishDto.getFlavors();
        for(DishFlavor df: flavors){
            //设置时间
            df.setCreateTime(LocalDateTime.now());
            df.setUpdateTime(LocalDateTime.now());
            //获得当前登录用户的id
            df.setCreateUser(dishDto.getCreateUser());
            df.setUpdateUser(dishDto.getCreateUser());
            //设置id
            Random random=new Random();
            Integer number=random.nextInt(90000)+10000;
            String idStr=System.currentTimeMillis()+String.valueOf(number);
            Long id=Long.parseLong(idStr);
            df.setId(id);
            df.setDishId(dishDto.getId());
            df.setIsDeleted(0);
            dishFlavorService.insert(df);
        }
    }
}
