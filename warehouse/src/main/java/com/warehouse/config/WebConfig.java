package com.warehouse.config;

import com.warehouse.interceptor.LogonInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author promefire
 * @date 2024/5/5 15:42
 * description: 拦截器配置类
 *              注：需要写Configuration注解
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        创建一个自定义的拦截器，增加拦截的的路径
        registry.addInterceptor(new LogonInterceptor()).addPathPatterns("/user/**");
    }
}
