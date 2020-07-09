package com.lzq.security.springmvc.service;

import com.lzq.security.springmvc.model.AuthenticationRequest;
import com.lzq.security.springmvc.model.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created in      15:18 2020/7/9
 *
 * @Description: [用户认证]
 * Copyright:      Copyright (c) 2016
 * Department:     研发部
 * @Author: LaiZQ
 * @version: 1.0
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    /**
     * 校验用户身份信息是否合法
     * @param authenticationRequest 用户认证请求，账户和密码
     * @return
     */
    @Override
    public UserDto authentication(AuthenticationRequest authenticationRequest) {
        //校验参数是否为空
        if (authenticationRequest==null|| StringUtils.isEmpty(authenticationRequest.getUsername())
        ||StringUtils.isEmpty(authenticationRequest.getPassword())){
            throw new RuntimeException("账号或密码为空");
        }
        //根据帐号去查询数据库，这里测试程序采用模拟方法
        UserDto user = getUserDto(authenticationRequest.getUsername());
        //判断用户是否为空
        if(user==null){
            throw new RuntimeException("查无此人");
        }
        //校验密码
        if (!user.getPassword().equals(authenticationRequest.getPassword())){
            throw new RuntimeException("账号或密码错误");
        }
        //认证通过，返回用户身份信息
        return user;
    }

    //根据账号查询用户信息
    private UserDto getUserDto(String username){
        return userMap.get(username);
    }
    //用户信息
    private Map<String,UserDto>userMap=new HashMap<>();
    {
        Set<String>authorities1=new HashSet<>();
        authorities1.add("p1");//这个p1我们认为让他和/r/r1对应
        Set<String>authorities2=new HashSet<>();
        authorities2.add("p2");//这个p2我们认为让他和/r/r2对应
        userMap.put("zhangsan", new UserDto("1010","zhangsan","123","张三","133443",authorities1));
        userMap.put("lis", new UserDto("1011","lis","456","李四","144553",authorities2));
    }
}
