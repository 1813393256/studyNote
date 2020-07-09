package com.lzq.security.springmvc.controller;

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

    @GetMapping(value = "/r/r1",produces = "text/plain;charset=utf-8")
    private String r1(HttpSession session){
       return null;
    }

    @GetMapping(value = "/r/r2",produces = "text/plain;charset=utf-8")
    private String r2(HttpSession session){

        return null;
    }

    @RequestMapping(value = "/loginSuccess",produces = "text/plain;charset=utf-8")
    public String loginSuccess(){
        System.out.println("ssssssssssss");
        return "登陆成功";
    }

}
