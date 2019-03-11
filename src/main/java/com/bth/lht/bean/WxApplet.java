package com.bth.lht.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: lht
 * @description: 小程序基本信息
 * @author: Antony
 * @create: 2018-12-21 12:50
 **/
@Data
@Component
@ConfigurationProperties(prefix="wx-applet")
public class WxApplet {
    private  String appid;
    private  String secret;
    private  String grant_type;
}
