package com.atguigu.atcrowdfunding.manager.service;

import com.atguigu.atcrowdfunding.bean.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionService {
    Permission queryParentPermission();

    List<Permission> queryPermissionByParenetID(Integer id);

    List<Permission> selectAllPermission();

    int insert(Permission permission);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission permission);

    int deletePermission(Integer id);

    List<Permission> queryPermissionByUserIDAndRoleID(Map<String, Object> params);
}
