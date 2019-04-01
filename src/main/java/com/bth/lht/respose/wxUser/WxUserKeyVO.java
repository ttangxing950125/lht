package com.bth.lht.respose.wxUser;

import lombok.Data;

/**
 * @program: lht
 * @description: 微信用户响应实体
 * @author: Antony
 * @create: 2019-03-19 11:26
 **/
@Data
public class WxUserKeyVO {
    private String session_key;
    private String openid;
}
