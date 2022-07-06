package com.wuzhicheng.pigstore.mapper;

import com.wuzhicheng.pigstore.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuzhicheng
 * @create 2022-06-30 23:11
 */
@Mapper
public interface EmployeeMapper {
    List<Employee> selectByUsername(String username);
    List<Employee> selectAll();
    void addItem();
    int save(Employee employee);
    List<Employee> selectByPage(@Param("begin") int begin,
                                @Param("end") int end,
                                @Param("name") String name);
    int getLength();
    int update(Employee employee);
    List<Employee> selectById(Long id);
}
