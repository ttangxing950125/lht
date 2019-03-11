package com.bth.lht.exception;

import com.bth.lht.respose.base.BaseResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: lht
 * @description: 异常捕获
 * @author: Antony
 * @create: 2018-12-21 12:33
 **/
@RestControllerAdvice
public class ResponseExceptionHandler {
    @ExceptionHandler(ResponseException.class)
    public BaseResponse handler(ResponseException exception){
        return new BaseResponse(exception.getCode(),exception.getDesc());
    }
}