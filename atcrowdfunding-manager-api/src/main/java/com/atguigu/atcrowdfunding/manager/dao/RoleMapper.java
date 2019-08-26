package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.RoleExample;
import com.atguigu.atcrowdfunding.bean.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> queryRoleListByParams(Map<String, Object> params);

    List<Permission> queryPermissionByRoleID(Integer roleId);

    void deletePermissionByRoleID(Integer roleId);

    Integer saveRolePermission(List<RolePermission> rolePermissionList);

    List<Role> queryRoleInfo(Integer id);
}