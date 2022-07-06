package com.wuzhicheng.pigstore.service;

import com.wuzhicheng.pigstore.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuzhicheng
 * @create 2022-06-30 23:14
 */
public interface EmployeeService {
    List<Employee> selectByUsername(String username);
    List<Employee> selectAll();
    void addItem();
    int save(Employee employee);
    List<Employee> selectByPage(int begin, int end, String name);
    int getLength();
    int update(Employee employee);
    List<Employee> selectById(Long id);
}
