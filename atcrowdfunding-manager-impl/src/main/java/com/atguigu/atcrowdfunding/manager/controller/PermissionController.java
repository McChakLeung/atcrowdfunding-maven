package com.atguigu.atcrowdfunding.manager.controller;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/index")
    public String index(){
        return "/permission/index";
    }

    /**
     * Demo3 -- 数据库交互：递归调用
     *
     * 递归：
     * 1.基准条件
     * 2.范围逐渐缩小
     * 3.调用自身函数
     *
     * 缺点：
     * 1.多次查询数据库，数据库压力大
     * 2.递归调用会在内存栈区压栈，容易造成内存溢出
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/loadData")
    public Object loadData(){

        AjaxResult result = new AjaxResult();

        try{
            //设置父节点list
            List<Permission> root = new ArrayList();

            //查询数据库父节点
            Permission parent = permissionService.queryParentPermission();
            root.add(parent);

            //设置一个childrenList集合，用于接收从数据库中查询的子节点
            queryChildrenPerminssion(parent);

            result.setDatas(root);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("加载许可树失败");
        }
        return result;
    }

    private void queryChildrenPerminssion(Permission parent){
        List<Permission> childrenList = permissionService.queryPermissionByParenetID(parent.getId());
        parent.setChildren(childrenList);

        //遍历子节点(foreach循环）
        for (Permission children: childrenList) {
            queryChildrenPerminssion(children);
        }
    }

//    /**
//     * Demo2-- 数据库交互：实现三级菜单加载
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/loadData")
//    public Object loadData(){
//
//        AjaxResult result = new AjaxResult();
//
//        try{
//            //设置父节点list
//            List<Permission> root = new ArrayList();
//
//            //查询数据库父节点
//            Permission parent = permissionService.queryParentPermission();
//            root.add(parent);
//
//            //设置一个childrenList集合，用于接收从数据库中查询的子节点
//            List<Permission> childrenList = permissionService.queryPermissionByParenetID(parent.getId());
//            parent.setChildren(childrenList);
//
//            //遍历子节点(foreach循环）
//            for (Permission children: childrenList) {
//                List<Permission> sonList = permissionService.queryPermissionByParenetID(children.getId());
//                children.setChildren(sonList);
//            }
//
//            result.setDatas(root);
//            result.setSuccess(true);
//        }catch (Exception e){
//            e.printStackTrace();
//            result.setSuccess(false);
//            result.setMessage("加载许可树失败");
//        }
//        return result;
//    }

    //Demo1 -- 无数据库交互（模拟实现）
//    @ResponseBody
//    @RequestMapping("/loadData")
//    public Object loadData(){
//
//        AjaxResult result = new AjaxResult();
//
//        try{
//
//            //设置父节点list
//            List<Permission> root = new ArrayList();
//
//            //设置父节点
//            Permission parent = new Permission();
//            parent.setName("系统根节点");
//            parent.setOpen(true);
//            root.add(parent);
//
//            //设置子节点
//            //设置一个childrenList集合，用于接收子节点
//            List<Permission> childrenList = new ArrayList();
//            Permission child1 = new Permission();
//            child1.setName("子节点1");
//            Permission child2 = new Permission();
//            child2.setName("子节点2");
//
//            //设置关联关系
//            childrenList.add(child1);
//            childrenList.add(child2);
//            parent.setChildren(childrenList);
//
//            result.setDatas(root);
//
//            result.setSuccess(true);
//        }catch (Exception e){
//            e.printStackTrace();
//            result.setSuccess(false);
//            result.setMessage("加载许可树失败");
//        }
//        return result;
//    }
}
