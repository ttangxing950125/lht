package com.bth.lht.bean;

import lombok.Data;

import java.util.Map;

@Data
public class WxmssVo {
    private String touser;//用户openid
    private String template_id;//模版id
    private String page = "index";//默认跳到小程序首页
    private String form_id;//收集到的用户formid
    private String emphasis_keyword = "keyword1.DATA";//放大那个推送字段
    private Map<String, TemplateData> data;//推送文字private Map<String, TemplateData> data;//推送文字
}