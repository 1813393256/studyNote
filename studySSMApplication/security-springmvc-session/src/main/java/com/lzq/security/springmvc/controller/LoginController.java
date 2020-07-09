package com.lzq.security.springmvc.controller;

import com.lzq.security.springmvc.model.AuthenticationRequest;
import com.lzq.security.springmvc.model.UserDto;
import com.lzq.security.springmvc.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created in      15:34 2020/7/9
 * Title:          初九数据科技后台管理系统_[]
 *
 * @Description: []
 * Copyright:      Copyright (c) 2016
 * Department:     研发部
 * @Author: LaiZQ
 * @version: 1.0
 */
@RestController
public class LoginController {
    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/login",produces = "text/plain;charset=utf-8")
    private String login(AuthenticationRequest authenticationRequest, HttpSession session){
        UserDto userDto = authenticationService.authentication(authenticationRequest);
        session.setAttribute(userDto.SESSION_USER_KEY,userDto);
        return userDto.getUsername()+"登陆成功";
    }

    @GetMapping(value = "/logout",produces = "text/plain;charset=utf-8")
    private String logout(HttpSession session){
        session.invalidate();
        return "退出成功";
    }

    @GetMapping(value = "/r/r1",produces = "text/plain;charset=utf-8")
    private String r1(HttpSession session){
        String fullname=null;
        Object object=session.getAttribute(UserDto.SESSION_USER_KEY);
        if (object==null){
            fullname="匿名";
        }else {
            UserDto userDto=(UserDto) object;
            fullname=userDto.getFullname();
        }
        return fullname+"访问资源r1";
    }

    @GetMapping(value = "/r/r2",produces = "text/plain;charset=utf-8")
    private String r2(HttpSession session){
        String fullname=null;
        Object object=session.getAttribute(UserDto.SESSION_USER_KEY);
        if (object==null){
            fullname="匿名";
        }else {
            UserDto userDto=(UserDto) object;
            fullname=userDto.getFullname();
        }
        return fullname+"访问资源r1";
    }
}
