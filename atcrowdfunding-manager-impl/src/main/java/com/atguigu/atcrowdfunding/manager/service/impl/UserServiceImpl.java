package com.atguigu.atcrowdfunding.manager.service.impl;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.exception.LoginException;
import com.atguigu.atcrowdfunding.manager.dao.UserMapper;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.MD5Util;
import com.atguigu.atcrowdfunding.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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

//    @Override
//    public Page<User> selectUserList(Integer pageno, Integer pagesize) {
//
//        Page<User> page = new Page<>(pageno,pagesize);
//        //查询用户列表数据
//        List<User> datas = userMapper.selectUserList(page.getStartline(),pagesize);
//        page.setDatalist(datas);
//
//        //查询用户总数
//        Integer totalsize = userMapper.selectCount();
//        //将查询结果存放到公共的Page类中
//        page.setTotalsize(totalsize);
//        return page;
//    }

    @Override
    public Page<User> selectUserList(Map<String, Object> params) {
        Page<User> page = new Page<>((Integer) params.get("pageno"),(Integer)params.get("pagesize"));
        //查询用户列表数据
        params.put("startline",page.getStartline());
        List<User> datas = userMapper.selectUserList(params);
        page.setDatalist(datas);

        //查询用户总数
        Integer totalsize = userMapper.selectCount(params);
        //将查询结果存放到公共的Page类中
        page.setTotalsize(totalsize);
        return page;
    }

    @Override
    public Integer saveUser(User user) {
        //将日期类转换成字符串
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String createtime = simpleDateFormat.format(new Date());
        user.setCreatetime(createtime);
        user.setUserpswd(MD5Util.digest(Const.PASSWORD));
        return userMapper.insert(user);
    }

    @Override
    public User selectUserByID(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Integer deleteUserById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteUserByBatch(List<User> userList) {
//        //通过foreach循环的方式删除
//        Integer totalcount = 0;
//        for (User user: userList) {
//            Integer count = userMapper.deleteByPrimaryKey(user.getId());
//            totalcount += count;
//        }
//        if(totalcount!=userList.size()){
//            throw new RuntimeException("批量删除失败");
//        }
        //通过mybatis循环删除数据
        return  userMapper.deleteUserByBatch(userList);
//        return totalcount;
    }

    @Override
    public List<Role> queryAllRole() {
        return userMapper.queryAllRole();
    }

    @Override
    public List<Integer> queryRoleById(Integer id) {
        return userMapper.queryRoleById(id);
    }
}
