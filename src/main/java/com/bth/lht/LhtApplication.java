package com.bth.lht;

import com.bth.lht.util.HttpUtils;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.http.HttpResponse;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(scanBasePackages = "com.bth.lht")
@MapperScan({"com.bth.lht.dao.team","com.bth.lht.dao.project","com.bth.lht.dao.user"})
public class LhtApplication {

    public static void main(String[] args) {
        send();
        SpringApplication.run(LhtApplication.class, args);
    }
//
//        // 在某配置类中添加如下内容
//    // 监听的http请求的端口,需要在application配置中添加http.port=端口号  如80
    @Value("${http.port}")
    String httpPort;

    //正常启用的https端口 如443
    @Value("${server.port}")
    String httpsPort;

    // springboot2 写法
    @Bean
    public TomcatServletWebServerFactory servletContainer(Connector connector) {
        TomcatServletWebServerFactory tomcat=new TomcatServletWebServerFactory(){

            @Override

            protected void postProcessContext(Context context) {

                SecurityConstraint securityConstraint=new SecurityConstraint();

                securityConstraint.setUserConstraint("CONFIDENTIAL");

                SecurityCollection collection=new SecurityCollection();

                collection.addPattern("/*");

                securityConstraint.addCollection(collection);

                context.addConstraint(securityConstraint);

            }

        };

        tomcat.addAdditionalTomcatConnectors(connector);

        return tomcat;
    }

    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        //Connector监听的http的端口号
        connector.setPort(Integer.parseInt(httpPort));
        connector.setSecure(false);
        //监听到http的端口号后转向到的https的端口号
        connector.setRedirectPort(Integer.parseInt(httpsPort));
        return connector;
    }

    //短信接口测试
    public static void   send(){
        String host = "http://dingxin.market.alicloudapi.com";
        String path = "/dx/sendSms";
        String method = "POST";
        String appcode = "6bc3dde6c05449018cbdee09e96e7bb3";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", "18483679330");
        querys.put("param", "code:1234");
        querys.put("tpl_id", "TP1711063");
        Map<String, String> bodys = new HashMap<String, String>();


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }






