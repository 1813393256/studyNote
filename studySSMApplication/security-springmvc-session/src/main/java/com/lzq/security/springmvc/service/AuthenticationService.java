package com.lzq.security.springmvc.service;

import com.lzq.security.springmvc.model.AuthenticationRequest;
import com.lzq.security.springmvc.model.UserDto;

/**
 * Created in      15:11 2020/7/9
 *
 * @Description: []
 * Copyright:      Copyright (c) 2016
 * Department:     研发部
 * @Author: LaiZQ
 * @version: 1.0
 */
public interface AuthenticationService {

    /**
     * 用户认证
     * @param authenticationRequest 用户认证请求，账户和密码
     * @return 认证成功的用户信息
     */
    UserDto authentication(AuthenticationRequest authenticationRequest);
}
