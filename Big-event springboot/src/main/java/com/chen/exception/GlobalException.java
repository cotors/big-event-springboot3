package com.chen.exception;

import com.chen.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: Cotors
 * @create 2024-03-15-11:30
 */
@RestControllerAdvice
public class GlobalException {

    //获取异常情况,定义全局异常处理，用于处理返回与Result相符合的异常信息
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage())?e.getMessage() : "操作失败");
    }

}
