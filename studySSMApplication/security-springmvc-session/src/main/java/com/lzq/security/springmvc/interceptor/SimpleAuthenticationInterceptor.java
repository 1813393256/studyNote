package com.lzq.security.springmvc.interceptor;

import com.lzq.security.springmvc.model.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created in      16:17 2020/7/9
 *
 * @Description: [简单拦截器，权限拦截]
 * Copyright:      Copyright (c) 2016
 * Department:     研发部
 * @Author: LaiZQ
 * @version: 1.0
 */
@Component
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在这个方法中校验用户请求的url是否在用户的权限范围内
        //取出用户的身份信息
        Object object = request.getSession().getAttribute(UserDto.SESSION_USER_KEY);
        if (object==null){
            //没有认证，提示登陆
            writeCpntent(response,"请登录");
        }
        UserDto userDto= (UserDto) object;
        String requestURI = request.getRequestURI();
        if (userDto.getAuthorities().contains("p1")&&requestURI.contains("/r/r1")){
            return true;
        }
        if (userDto.getAuthorities().contains("p2")&&requestURI.contains("/r/r2")){
            return true;
        }

        writeCpntent(response, "没有权限，拒绝访问");
        return false;


    }

    //响应信息给客户端
    private void writeCpntent(HttpServletResponse response,String msg) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println(msg);
        writer.close();
    }
}
