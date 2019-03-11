package com.bth.lht.request.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @program: lht
 * @description: 用户请求
 * @author: Antony
 * @create: 2018-12-21 18:56
 **/
@Data
@ApiModel("用户信息请求实体")
public class UserInfoRequest {
    private String username;
    private String password;
}
