package com.bth.lht.respose.base;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @program: lht
 * @description: 响应基类
 * @author: Antony
 * @create: 2018-12-21 12:35
 **/
@Data
public class BaseResponse {
    public BaseResponse(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
    public BaseResponse() {
    }
    /**
     * 描述信息
     */
    private Integer status = 200;

    /**
     * 描述信息
     */
    private String desc = HttpStatus.OK.getReasonPhrase();
}
