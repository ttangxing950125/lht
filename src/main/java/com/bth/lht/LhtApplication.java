package com.bth.lht;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication

public class LhtApplication {

    public static void main(String[] args) {
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
        connector.setPort(80);
        connector.setSecure(false);
        //监听到http的端口号后转向到的https的端口号
        connector.setRedirectPort(443);
        return connector;
    }

}

