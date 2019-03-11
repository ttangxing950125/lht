package com.bth.lht.util;

import com.aliyuncs.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * @program: lht
 * @description: 基础工具类
 * @author: Antony
 * @create: 2018-12-21 12:49
 **/
public class Util {
    /**
     * 获取验证码（6位）
     * @return
     */
    public static String getVerificationCode(){
        Random random = new Random();
        String result="";
        for (int i=0;i<6;i++)
        {
            result+=random.nextInt(10);
        }
        return result;
    }

    /**
     * 限制同一ip的访问
     * @param request
     * @return
     */
    public static boolean canSend(HttpServletRequest request){
        //多长时间访问一次
        int time = 60;
        String timestamp = String.valueOf(System.currentTimeMillis()).substring(0, 10);
        if (request.getSession().getAttribute(getIp(request)) == null) {
            request.getSession().setAttribute(getIp(request), timestamp);
            System.out.println("第一次" + getIp(request) + "---" + timestamp);
            return true;
        } else {
            int i = Integer.parseInt(timestamp) - Integer.parseInt((String) request.getSession().getAttribute(getIp(request)));
            if (i < time) {
                System.out.println("请求太快 请" + (time - i) + "秒后");
                return false;
            } else {
                request.getSession().setAttribute(getIp(request), timestamp);
                return true;
                // SendSmsResponse response = Sms.sendSms("18581892779", "134563");
            }
        }
    }


    /**
     * 获取访问者IP地址
     * <p>在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。</p>
     * <p>本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)。</p>
     * <p>如果还不存在则调用Request.getRemoteAddr()。</p>
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }
}
