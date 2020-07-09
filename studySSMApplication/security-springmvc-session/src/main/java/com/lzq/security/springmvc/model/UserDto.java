package com.lzq.security.springmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * Created in      15:13 2020/7/9
 *
 * @Description: [用户身份信息]
 * Copyright:      Copyright (c) 2016
 * Department:     研发部
 * @Author: LaiZQ
 * @version: 1.0
 */
@Data
@AllArgsConstructor
public class UserDto {
    public static final String SESSION_USER_KEY="_user";
    //用户身份信息
    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;

    /**
     * 用户权限
     */
    private Set<String>authorities;
}
