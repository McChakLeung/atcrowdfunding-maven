package com.atguigu.atcrowdfunding.manager.service.impl;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.manager.dao.RoleMapper;
import com.atguigu.atcrowdfunding.manager.service.RoleService;
import com.atguigu.atcrowdfunding.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Page<Role> queryRoleListByParams(Map<String, Object> params) {

        List<Role> roleList = roleMapper.queryRoleListByParams(params);

        Page<Role> page = new Page((Integer) params.get("pageno"),(Integer)params.get("pagesize"));

        page.setDatalist(roleList);

        return page;
    }
}
