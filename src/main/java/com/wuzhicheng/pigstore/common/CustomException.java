package com.wuzhicheng.pigstore.common;

/**
 * 自定义业务异常
 * @author wuzhicheng
 * @create 2022-07-04 23:06
 */
public class CustomException extends RuntimeException{
    public CustomException(String msg){
        super(msg);
    }
}
