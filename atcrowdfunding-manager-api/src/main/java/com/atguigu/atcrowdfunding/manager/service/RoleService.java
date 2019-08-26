package com.atguigu.atcrowdfunding.manager.service;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.util.Page;

import java.util.List;
import java.util.Map;

public interface RoleService {
    Page<Role> queryRoleListByParams(Map<String, Object> params);

    List<Permission> queryPermissionByRoleID(Integer roleId);

    Integer processAssignPermission(Integer roleId, List<Integer> ids);

    List queryRoleInfo(Integer id);
}
