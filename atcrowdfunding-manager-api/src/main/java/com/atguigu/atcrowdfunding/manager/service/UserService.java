package com.atguigu.atcrowdfunding.manager.service;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.bean.UserRole;
import com.atguigu.atcrowdfunding.util.Page;
import javafx.beans.binding.ObjectExpression;
import org.springframework.jdbc.support.nativejdbc.OracleJdbc4NativeJdbcExtractor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService {
    User selectUserByLoginAccAndUserPassword(Map<String, Object> params);

    //Page<User> selectUserList(Integer pageno, Integer pagesize);

    Page<User> selectUserList(Map<String,Object> params);

    Integer saveUser(User user);

    User selectUserByID(Integer id);

    Integer updateUser(User user);

    Integer deleteUserById(Integer id);

    Integer deleteUserByBatch(List<User> userList);

    List<Role> queryAllRole();

    List<Integer> queryRoleById(Integer id);

    void saveUserRoleByBatch(List<UserRole> userRoleList);

    void deleteUserRoleByBatch(List<UserRole> userRoleList);
}
