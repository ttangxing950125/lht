package com.bth.lht.exception;

import lombok.Data;

/**
 * @program: lht
 * @description:异常处理类
 * @author: Antony
 * @create: 2018-12-21 12:33
 **/
@Data
public class ResponseException extends RuntimeException{
    public ResponseException(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 响应代码
     */
    private Integer code;

    /**
     * 响应描述
     */
    private String desc;


}

