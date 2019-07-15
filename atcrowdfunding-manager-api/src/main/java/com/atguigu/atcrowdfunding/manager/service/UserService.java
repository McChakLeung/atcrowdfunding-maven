package com.atguigu.atcrowdfunding.manager.service;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.util.Page;

import java.util.Map;

public interface UserService {
    User selectUserByLoginAccAndUserPassword(Map<String, Object> params);

    Page<User> selectUserList(Integer pageno, Integer pagesize);

    void saveUser(User user);
}
