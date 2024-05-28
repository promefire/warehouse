package com.warehouse.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;

/**
 * @author promefire
 * @date 2024/5/5 15:29
 * description:
 */
public class LogonInterceptor implements HandlerInterceptor {


//    request:前段传过来的请求
//    response：给前端返回对应的信息
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler ){
        System.out.println("LoginIntecetor");
        return true;

    }
}
