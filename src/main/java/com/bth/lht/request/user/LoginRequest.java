package com.bth.lht.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: lht
 * @description: 登录授权请求
 * @author: Antony
 * @create: 2019-03-19 10:58
 **/
@Data
@ApiModel("用户登录请求")
public class LoginRequest {
    @ApiModelProperty("微信小程序，用户授权码")
    private String code;
    private UserRequest userRequest;

}
