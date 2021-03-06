package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.bean.UserExample;
import com.atguigu.atcrowdfunding.bean.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserByLoginAccAndUserPassword(Map<String, Object> params);

//    List<User> selectUserList(@Param("pageno") Integer pageno, @Param("pagesize") Integer pagesize);
//
//    Integer selectCount();

    List<User> selectUserList(Map<String, Object> params);

    Integer selectCount(Map<String, Object> params);

    Integer deleteUserByBatch(List<User> userList);

    List<Integer> queryRoleById(Integer id);

    List<Role> queryAllRole();

    void saveUserRoleByBatch(List<UserRole> userRoleList);

    void deleteUserRoleByBatch(List<UserRole> userRoleList);
}