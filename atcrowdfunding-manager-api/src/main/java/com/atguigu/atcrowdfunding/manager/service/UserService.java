package com.atguigu.atcrowdfunding.manager.service;

import com.atguigu.atcrowdfunding.bean.User;

import java.util.Map;

public interface UserService {
    User selectUserByLoginAccAndUserPassword(Map<String, Object> params);
}