package com.wuzhicheng.pigstore.service.impl;

import com.wuzhicheng.pigstore.entity.Employee;
import com.wuzhicheng.pigstore.mapper.EmployeeMapper;
import com.wuzhicheng.pigstore.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wuzhicheng
 * @create 2022-06-30 23:16
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public List<Employee> selectByUsername(String username) {
        return employeeMapper.selectByUsername(username);
    }

    @Override
    public List<Employee> selectAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public void addItem() {
        employeeMapper.addItem();
    }

    @Override
    public int save(Employee employee) {
        return employeeMapper.save(employee);
    }

    @Override
    public List<Employee> selectByPage(int begin, int end, String name) {
        return employeeMapper.selectByPage(begin,end,name);
    }

    @Override
    public int getLength() {
        return employeeMapper.getLength();
    }

    @Override
    public int update(Employee employee) {
        return employeeMapper.update(employee);
    }

    @Override
    public List<Employee> selectById(Long id) {
        return employeeMapper.selectById(id);
    }
}
