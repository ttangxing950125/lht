package com.bth.lht.request.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * @program: lht
 * @description: 用户请求
 * @author: Antony
 * @create: 2018-12-21 18:33
 **/
@Data
@ApiModel("绑定用户基本信息请求")
public class UserRequest {
    private String wxOpenid;
    private String wxNickName;
    private String avatarUrl;
    private BigDecimal integral;
    private Integer phoneNumber;
}
