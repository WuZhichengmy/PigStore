package com.wuzhicheng.pigstore.controller;

import com.wuzhicheng.pigstore.common.Page;
import com.wuzhicheng.pigstore.common.R;
import com.wuzhicheng.pigstore.dto.DishDto;
import com.wuzhicheng.pigstore.entity.Category;
import com.wuzhicheng.pigstore.entity.Dish;
import com.wuzhicheng.pigstore.entity.DishFlavor;
import com.wuzhicheng.pigstore.entity.Employee;
import com.wuzhicheng.pigstore.service.CategoryService;
import com.wuzhicheng.pigstore.service.DishFlavorService;
import com.wuzhicheng.pigstore.service.DishService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜品管理
 * @author wuzhicheng
 * @create 2022-07-05 22:40
 */
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    DishService dishService;
    @Autowired
    DishFlavorService dishFlavorService;
    @Autowired
    CategoryService categoryService;

    /**
     * 新增菜品
     * @param dishDto
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest req,
                          @RequestBody DishDto dishDto){
        System.out.println(dishDto);
        dishService.saveWithFlavor(dishDto, (Long) req.getSession().getAttribute("employee"));
        return R.success("创建成功");
    }

    /**
     * 菜品的分页查询请求
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        //由于前端需要的是菜品分类的名称而不是菜品分类的id，因此需要使用DishDto
        if(name==null){
            name="null";
        }
        List<Dish> dishes = dishService.selectByPage((page - 1) * pageSize, page * pageSize, name);
        List<DishDto> collect = dishes.stream().map((item) -> {
            Long categoryId = item.getCategoryId();
            Category byId = categoryService.getById(categoryId);
            DishDto dishDto = new DishDto();
            dishDto.setCategoryName(byId.getName());
            BeanUtils.copyProperties(item, dishDto);
            return dishDto;
        }).collect(Collectors.toList());
        int totLength=dishService.getLength();
        Page<DishDto> page1 = Page.getPage(collect, (long) totLength);
        return R.success(page1);
    }

    /**
     * 根据id查询菜品信息和对应的口味信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<DishDto> get(@PathVariable Long id){
        DishDto dishDto=dishService.getByIdWithFlavor(id);
        return R.success(dishDto);
    }

    /**
     * 修改菜品
     * @param dishDto
     * @return
     */
    @PutMapping
    public R<String> update(HttpServletRequest req,
                          @RequestBody DishDto dishDto){
        System.out.println(dishDto);
        dishService.updateWithFlavor(dishDto, (Long) req.getSession().getAttribute("employee"));
        return R.success("修改成功");
    }

}
