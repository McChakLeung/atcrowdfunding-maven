package com.atguigu.atcrowdfunding.manager.service.impl;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.exception.LoginException;
import com.atguigu.atcrowdfunding.manager.dao.UserMapper;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserByLoginAccAndUserPassword(Map<String, Object> params) {
        User user = userMapper.selectUserByLoginAccAndUserPassword(params);
        //自定义一个异常，抛出LoginException，该Exception定义在common项目中
//        if(user==null){
//            throw new LoginException("用户名或密码错误，请重新登录");
//        }
        return user;
    }

    @Override
    public Page<User> selectUserList(Integer pageno, Integer pagesize) {

        Page<User> page = new Page<>(pageno,pagesize);
        //查询用户列表数据
        List<User> datas = userMapper.selectUserList(page.getStartline(),pagesize);
        page.setDatalist(datas);

        //查询用户总数
        Integer totalsize = userMapper.selectCount();
        //将查询结果存放到公共的Page类中
        page.setTotalsize(totalsize);
        return page;
    }

    @Override
    public void saveUser(User user) {
        userMapper.insert(user);
    }
}
