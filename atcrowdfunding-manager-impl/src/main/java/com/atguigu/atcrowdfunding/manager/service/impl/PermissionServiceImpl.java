package com.atguigu.atcrowdfunding.manager.service.impl;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.manager.dao.PermissionMapper;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Permission queryParentPermission() {
        return permissionMapper.queryParentPermission();
    }

    @Override
    public List<Permission> queryPermissionByParenetID(Integer id) {
        return permissionMapper.queryPermissionByParenetID(id);
    }

    @Override
    public List<Permission> selectAllPermission() {
        return permissionMapper.selectAllPermission();
    }

    @Override
    public int insert(Permission permission) {
        return permissionMapper.insert(permission);
    }

    @Override
    public Permission selectByPrimaryKey(Integer id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Permission permission) {
        return permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Override
    public int deletePermission(Integer id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }
}
