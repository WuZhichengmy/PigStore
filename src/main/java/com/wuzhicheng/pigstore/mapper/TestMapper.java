package com.wuzhicheng.pigstore.mapper;

import com.wuzhicheng.pigstore.entity.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wuzhicheng
 * @create 2022-07-01 0:39
 */
@Mapper
public interface TestMapper {
    List<Test> selectAll();
}
