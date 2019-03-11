package com.bth.lht.respose.base;

import lombok.Data;

/**
 * @program: lht
 * @description: 单数据响应类
 * @author: Antony
 * @create: 2018-12-21 12:39
 **/
@Data
public class OneResponse<T> extends BaseResponse{
    private T data;
    public OneResponse(Integer status, String desc, T data) {
        super(status, desc);
        data = data;
    }
    public OneResponse() {
    }
}
