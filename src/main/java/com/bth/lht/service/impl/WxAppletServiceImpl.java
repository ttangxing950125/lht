package com.bth.lht.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bth.lht.bean.WxApplet;
import com.bth.lht.respose.wxUser.WxUserKeyVO;
import com.bth.lht.service.WxAppletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @program: lht
 * @description: 获取微信openid
 * @author: Antony
 * @create: 2019-03-19 11:27
 **/
@Service
@Slf4j
public class WxAppletServiceImpl implements WxAppletService {
    @Autowired
    private WxApplet wxApplet;
    @Override
    public WxUserKeyVO getSessionKeyOrOpenid(String code) {
        //微信端登录code值
        String wxCode = code;
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> requestUrlParam = new HashMap<String,String>();
        requestUrlParam.put("appid",wxApplet.getAppid());	//开发者设置中的appId
        requestUrlParam.put("secret", wxApplet.getSecret());	//开发者设置中的appSecret
        requestUrlParam.put("js_code", wxCode);	//小程序调用wx.login返回的code
        requestUrlParam.put("grant_type", wxApplet.getGrant_type());//默认参数
        String str =this.sendGet(url,requestUrlParam);
        WxUserKeyVO wxUserKeyVO = JSON.parseObject(str,new TypeReference<WxUserKeyVO>(){});
        System.out.println(str);
        return wxUserKeyVO;
    }

    public static String sendGet(String url, Map<String,String> requestUrlParam) {
        //解析参数
        String param = "";
        Iterator<String> it = requestUrlParam.keySet().iterator();
        while(it.hasNext()) {
            String key = it.next();
            param += key + "=" + requestUrlParam.get(key) + "&";
        }
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                // System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
