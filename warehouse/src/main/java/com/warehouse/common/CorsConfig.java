package com.warehouse.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author promefire
 * @date 2024/5/6 15:34
 * description:
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //允许跨域访问的路径
                //是否发送Cookie
                .allowCredentials(true)
                //允许跨域访问的源
                .allowedOriginPatterns("*")
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"}) //允许请求方法
                .allowedHeaders("*") //允许头部设置
                .exposedHeaders("*");
    }
}
