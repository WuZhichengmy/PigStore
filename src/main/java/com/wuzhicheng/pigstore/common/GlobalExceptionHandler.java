package com.wuzhicheng.pigstore.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局的异常处理器
 * @author wuzhicheng
 * @create 2022-07-03 14:14
 */
@ControllerAdvice(annotations = {RestController.class}) //选择需要处理异常的类
@ResponseBody //需要以json返回
public class GlobalExceptionHandler {

    /**
     * 管理员新增员工时，避免员工名重复
     * @return
     */
    //@ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException exception){
        //判断是否是用户名重复
        if(exception.getMessage().contains(" Duplicate entry")){
            String username = exception.getMessage().split(" ")[2];
            String msg=username+"已存在";
            return R.error(msg);
        }
        return R.error("未知错误");
    }

    /**
     * 用来处理删除分类时的关联异常
     * @return
     */
    //@ExceptionHandler({CustomException.class})
    public R<String> exceptionHandler(CustomException exception){
        return R.error(exception.getMessage());
    }
}
