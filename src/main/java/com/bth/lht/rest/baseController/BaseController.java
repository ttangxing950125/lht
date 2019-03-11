package com.bth.lht.rest.baseController;

import com.bth.lht.respose.base.BaseResponse;
import com.bth.lht.respose.base.MultiResponse;
import com.bth.lht.respose.base.OneResponse;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * @program: lht
 * @description: 控制层基类
 * @author: Antony
 * @create: 2018-12-21 12:41
 **/
public class BaseController<T> {
    public BaseResponse successSave() {
        return new BaseResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
    }

    public BaseResponse successUpdate() {
        return new BaseResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
    }

    public BaseResponse successDelete() {
        return new BaseResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
    }

    public OneResponse<T> successOne(T data) {
        return new OneResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }

    public MultiResponse<T> successMulti(List<T> data) {
        return new MultiResponse<T>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }
}
