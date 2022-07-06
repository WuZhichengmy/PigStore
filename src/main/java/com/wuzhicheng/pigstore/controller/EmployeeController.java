package com.wuzhicheng.pigstore.controller;

import com.wuzhicheng.pigstore.common.Page;
import com.wuzhicheng.pigstore.common.R;
import com.wuzhicheng.pigstore.entity.Employee;
import com.wuzhicheng.pigstore.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 * @author wuzhicheng
 * @create 2022-06-30 23:18
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    /**
     * 用于员工登录
     * @return 员工对象
     */
    @PostMapping("/login")//->/emoloyee/login
    public R<Employee> login(HttpServletRequest req,
                             @RequestBody Employee employee){
        //获取传入的密码并使用md5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //查询数据库，根据提交的用户名
        Employee emp = employeeService.selectByUsername(employee.getUsername()).get(0);

        //判断查询结果
        if(emp==null){
            return R.error("登录失败");
        }

        //密码比对
        if(!emp.getPassword().equals(password)){
            return R.error("登录失败");
        }

        //查看员工状态
        if(emp.getStatus()==0){
            return R.error("已禁用");
        }

        //登录成功
        req.getSession().setAttribute("employee",emp.getId());//登录成功，保存session

        return R.success(emp);
    }

    /**
     * 员工退出登录
     * @return 信息
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest req){
        req.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     * 新增员工
     * @param req
     * @return
     */
    @PostMapping //由于请求路径就是employee
    public R<String> save(HttpServletRequest req,
                          @RequestBody Employee employee){
        //设置初始密码
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        //设置时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        //获得当前登录用户的id
        Long nowEmp = (Long) req.getSession().getAttribute("employee");
        employee.setCreateUser(nowEmp);
        employee.setUpdateUser(nowEmp);
        //设置新用户的id
        Random random=new Random();
        Integer number=random.nextInt(90000)+10000;
        String idStr=System.currentTimeMillis()+String.valueOf(number);
        Long id=Long.parseLong(idStr);
        employee.setId(id);
        //设置status
        employee.setStatus(1);
        employeeService.save(employee);
        return R.success("新增员工成功");
    }

    /**
     * 员工信息分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        if(name==null){
            name="null";
        }
        List<Employee> employees = employeeService.selectByPage((page - 1) * pageSize, page * pageSize, name);
        System.out.println(employees);
        int totLength=employeeService.getLength();
        Page<Employee> page1 = Page.getPage(employees, (long) totLength);
        System.out.println(page1.getRecords());
        return R.success(page1);
    }

    /**
     * 根据id修改员工信息
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> update(HttpServletRequest req,
                            @RequestBody Employee employee){
        //从数据库中找到对应的员工
        Employee employee1 = employeeService.selectById(employee.getId()).get(0);
        employee1.setUpdateTime(LocalDateTime.now());
        employee1.setUpdateUser((Long) req.getSession().getAttribute("employee"));
        //更新状态
        employee1.setStatus(employee.getStatus());
        if(employee.getUsername()!=null){
            employee1.setUsername(employee.getUsername());
        }
        if(employee.getName()!=null){
            employee1.setName(employee.getName());
        }
        if(employee.getPhone()!=null){
            employee1.setPhone(employee.getPhone());
        }
        if(employee.getSex()!=null){
            employee1.setSex(employee.getSex());
        }
        if(employee.getIdNumber()!=null){
            employee1.setIdNumber(employee.getIdNumber());
        }
        employeeService.update(employee1);
        System.out.println(employee1);
        return R.success("保存成功");
    }

    /**
     * 获取用户信息，用于修改用户时的回显
     * @param id
     * @return
     */
    @GetMapping("/{id}") //id为路径变量，写在路径中
    public R<Employee> getById(@PathVariable Long id){
        Employee employee=employeeService.selectById(id).get(0);
        return R.success(employee);
    }
}
