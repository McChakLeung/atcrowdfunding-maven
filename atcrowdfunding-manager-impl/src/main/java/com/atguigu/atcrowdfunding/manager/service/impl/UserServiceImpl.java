package com.atguigu.atcrowdfunding.manager.service.impl;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.exception.LoginException;
import com.atguigu.atcrowdfunding.manager.dao.UserMapper;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserByLoginAccAndUserPassword(Map<String, Object> params) {
        User user = userMapper.selectUserByLoginAccAndUserPassword(params);
        //自定义一个异常，抛出LoginException，该Exception定义在common项目中
        if(user==null){
            throw new LoginException("用户名或密码错误，请重新登录");
        }
        return user;
    }
}
