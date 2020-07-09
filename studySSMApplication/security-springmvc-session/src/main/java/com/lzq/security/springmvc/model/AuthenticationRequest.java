package com.lzq.security.springmvc.model;

import lombok.Data;

/**
 * Created in      15:14 2020/7/9
 *
 * @Description: [认证请求参数]
 * Copyright:      Copyright (c) 2016
 * Department:     研发部
 * @Author: LaiZQ
 * @version: 1.0
 */
@Data
public class AuthenticationRequest {
    //认证请求参数，账号，密码。。。
    private String username;//用户名
    private String password;//密码
}
