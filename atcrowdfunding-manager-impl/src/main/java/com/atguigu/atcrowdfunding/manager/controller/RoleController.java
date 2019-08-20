package com.atguigu.atcrowdfunding.manager.controller;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.manager.service.RoleService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "role/index";
    }

    @ResponseBody
    @RequestMapping("/queryPage")
    public Object queryPage(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
                            @RequestParam(value = "pagesize",required = false,defaultValue = "10") Integer pagesize){
        AjaxResult result = new AjaxResult();
        try{
            //定义一个map集合存放传入的参数
            Map<String,Object> params = new HashMap();
            params.put("pageno",pageno);
            params.put("pagesize",pagesize);
            Page<Role> page = roleService.queryRoleListByParams(params);

            //判断返回的集合是否为空，如果为null就没有必要在前端拼接数据
            if(page.getDatalist() == null){
                result.setMessage("无查询结果");
                result.setSuccess(false);
                return result;
            }
            //将page数据封装到result中
            result.setPage(page);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("查询失败，请联系管理员处理");
        }
        return result;
    }



}
