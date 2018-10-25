package com.qfedu.myoaproject2.config.web;


import com.qfedu.myoaproject2.web.listener.MyActivitiInit;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.util.EventListener;

/**
 * @Auther: Kam
 * @Date: 下午 10:43 2018-10-23
 * @Description: web层配置
 * @Version: 1.0
 */
@Configuration
public class WebConfig {

    /**
     * 配置上传文件解析器
     *
     * @return
     */
    @Bean
    public org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        multipartResolver.setMaxInMemorySize(100 * 1024 * 1024);
        multipartResolver.setMaxUploadSize(10 * 1024 * 1024);
        return multipartResolver;
    }

    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
        ServletListenerRegistrationBean<EventListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>(new MyActivitiInit());
        return servletListenerRegistrationBean;
    }
}





