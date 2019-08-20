package com.atguigu.atcrowdfunding.manager.service;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.util.Page;

import java.util.Map;

public interface RoleService {
    Page<Role> queryRoleListByParams(Map<String, Object> params);
}
