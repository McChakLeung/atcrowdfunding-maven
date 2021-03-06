package com.atguigu.atcrowdfunding.manager.service.impl;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.RolePermission;
import com.atguigu.atcrowdfunding.manager.dao.RoleMapper;
import com.atguigu.atcrowdfunding.manager.service.RoleService;
import com.atguigu.atcrowdfunding.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Page<Role> queryRoleListByParams(Map<String, Object> params) {

        //初始化Page对象
        Page<Role> page = new Page((Integer) params.get("pageno"),(Integer)params.get("pagesize"));
        //根据初始化Page类对象获得起始行的值
        Integer startline = page.getStartline();
        //将startline存入到参数集合中
        params.put("startline",startline);
        //将数据库中查询出来的Role数据查询出来并存放到page对象中，将page对象返回给控制器
        List<Role> roleList = roleMapper.queryRoleListByParams(params);
        page.setDatalist(roleList);
        return page;
    }

    @Override
    public List<Permission> queryPermissionByRoleID(Integer roleId) {
        return roleMapper.queryPermissionByRoleID(roleId);
    }

    @Override
    public Integer processAssignPermission(Integer roleId, List<Integer> ids) {

        //删除roleId对应的权限
        roleMapper.deletePermissionByRoleID(roleId);

        //组装RolePermission类，否则无法插入到数据库中
        List<RolePermission> rolePermissionList = new ArrayList<>();

        for (Integer permissionID: ids) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleid(roleId);
            rolePermission.setPermissionid(permissionID);
            rolePermissionList.add(rolePermission);
        }

        //保存需要修改的数据到中间表
        Integer count = roleMapper.saveRolePermission(rolePermissionList);

        return count;
    }

    @Override
    public List queryRoleInfo(Integer id) {
        return roleMapper.queryRoleInfo(id);
    }
}
