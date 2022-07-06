package com.wuzhicheng.pigstore.controller;

import com.wuzhicheng.pigstore.common.Page;
import com.wuzhicheng.pigstore.common.R;
import com.wuzhicheng.pigstore.entity.Category;
import com.wuzhicheng.pigstore.entity.Employee;
import com.wuzhicheng.pigstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 * 分类管理
 * @author wuzhicheng
 * @create 2022-07-04 13:13
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 添加菜品
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest req,
                          @RequestBody Category category){
        //设置时间
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        //获得当前登录用户的id
        Long nowEmp = (Long) req.getSession().getAttribute("employee");
        category.setCreateUser(nowEmp);
        category.setUpdateUser(nowEmp);
        //设置新分类的id
        Random random=new Random();
        Integer number=random.nextInt(90000)+10000;
        String idStr=System.currentTimeMillis()+String.valueOf(number);
        Long id=Long.parseLong(idStr);
        category.setId(id);
        categoryService.save(category);
        return R.success("添加成功");
    }

    /**
     * 分页显示
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize){
        List<Category> categories = categoryService.selectByPage((page - 1) * pageSize, page * pageSize);
        int totLength=categoryService.getLength();
        Page<Category> page1 = Page.getPage(categories, (long) totLength);
        return R.success(page1);
    }

    /**
     * 根据id删除分类
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long id){
        int i=categoryService.removeById(id);
        System.out.println(id);
        return R.success("删除成功");
    }

    /**
     * 根据id修改分类信息
     * @param category
     * @return
     */
    @PutMapping
    public R<String> update(HttpServletRequest req,
                            @RequestBody Category category){
        //设置时间
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        //获得当前登录用户的id
        Long nowEmp = (Long) req.getSession().getAttribute("employee");
        category.setCreateUser(nowEmp);
        category.setUpdateUser(nowEmp);
        categoryService.updateById(category);
        return R.success("修改成功");
    }

    /**
     * 根据条件查询分类数据
     * @param category
     * @return
     */
    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        List<Category> categories = categoryService.selectByType(category);
        return R.success(categories);
    }
}
