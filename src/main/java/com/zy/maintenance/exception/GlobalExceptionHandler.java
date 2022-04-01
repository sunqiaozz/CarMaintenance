package com.zy.maintenance.exception;

import com.zy.maintenance.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 如果抛出异常调用该方法
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result handle(ServiceException se){
        return Result.error(se.getCode(),se.getMessage());
    }

}
