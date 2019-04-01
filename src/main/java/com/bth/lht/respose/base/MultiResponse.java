package com.bth.lht.respose.base;

import lombok.Data;

import java.util.List;

/**
 * @program: lht
 * @description: 多数据响应类
 * @author: Antony
 * @create: 2018-12-21 12:37
 **/
@Data
public class MultiResponse<T> extends BaseResponse {
    public List<T> data;

    public MultiResponse(Integer status, String desc, List<T> data) {
        super(status, desc);
        this.data = data;
    }


}
