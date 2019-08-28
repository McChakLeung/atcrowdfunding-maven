package com.atguigu.atcrowdfunding.manager.controller;

import com.atguigu.atcrowdfunding.bean.Glyphicon;
import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.manager.service.GlyphiconService;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "/permission/index";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "/permission/add";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Map map){
        Permission permission = permissionService.selectByPrimaryKey(id);
        map.put("permission",permission);
        return "/permission/update";
    }

    @ResponseBody
    @RequestMapping("/doUpdate")
    public Object doUpdate(Permission permission){
        AjaxResult result = new AjaxResult();

        try{
            int count = permissionService.updateByPrimaryKeySelective(permission);
            result.setSuccess(count>0);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("更新许可树失败");
        }

        return result;

    }


    @ResponseBody
    @RequestMapping("/doAdd")
    public Object doAdd(Integer pid, Permission permission){
        AjaxResult result = new AjaxResult();

        try{
            permission.setPid(pid);
            int count = permissionService.insert(permission);
            result.setSuccess(count>0);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("添加许可树失败");
        }

        return result;

    }

    @ResponseBody
    @RequestMapping("/deletePermission")
    public Object deletePermission(Integer id){
        AjaxResult result = new AjaxResult();

        try{
            int count = permissionService.deletePermission(id);
            result.setSuccess(count>0);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("删除许可树失败");
        }

        return result;

    }

    /**
     * Demo5 -- 数据库交互：优化循环嵌套（使用map）
     *
     * map与list的区别：map的key键是可以任意指定的，可以指定对象的id值，
     * 而list集合的get()方法是按集合的先后顺序查找数据，值相对固定，所以无法使用list集合实现该功能
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/loadData")
    public Object loadData(){

        long startTime = System.currentTimeMillis();

        AjaxResult result = new AjaxResult();

        try{

            //一次性从数据库中查询所有的数据
            List<Permission> permissionList = permissionService.selectAllPermission();

            //设置父节点list
            List<Permission> root = new ArrayList();

            //创建一个map集合
            Map<Integer,Permission> map = new HashMap<>();

            //提前遍历内层循环
            for(Permission innerPermission : permissionList){
                map.put(innerPermission.getId(),innerPermission);
            }

            //循环遍历

            for(Permission permission : permissionList){

                //判断当前对象是否为根节点
                if(permission.getPid() == null){
                    root.add(permission);
                }else{
                    Permission parent = map.get(permission.getPid());
                    parent.getChildren().add(permission);
                }
            }

            result.setDatas(root);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("加载许可树失败");
        }

        long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);

        return result;

    }


//    /**
//     * Demo5 -- 数据库交互：优化循环嵌套（使用list）(失败）
//     *
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/loadData")
//    public Object loadData(){
//
//        long startTime = System.currentTimeMillis();
//
//        AjaxResult result = new AjaxResult();
//
//        try{
//
//            //一次性从数据库中查询所有的数据
//            List<Permission> permissionList = permissionService.selectAllPermission();
//
//            //设置父节点list
//            List<Permission> root = new ArrayList();
//
////            //提前遍历内层循环
////            for(Permission innerPermission : permissionList){
////
////            }
//
//            //循环遍历
//
//            for(Permission permission : permissionList){
//
//                //判断当前对象是否为根节点
//                if(permission.getPid() == null){
//                    root.add(permission);
//                }else{
//                    Permission parent = permissionList.get(permission.getPid());
//                    parent.getChildren().add(permission);
//                }
//            }
//
//            result.setDatas(root);
//            result.setSuccess(true);
//        }catch (Exception e){
//            e.printStackTrace();
//            result.setSuccess(false);
//            result.setMessage("加载许可树失败");
//        }
//
//        long endTime = System.currentTimeMillis();
//
//        System.out.println(endTime - startTime);
//
//        return result;
//
//    }

//    /**
//     * Demo4 -- 数据库交互：一次性从数据库读取所有数据
//     *
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/loadData")
//    public Object loadData(){
//
//        AjaxResult result = new AjaxResult();
//
//        try{
//
//            //一次性从数据库中查询所有的数据
//            List<Permission> permissionList = permissionService.selectAllPermission();
//
//            //设置父节点list
//            List<Permission> root = new ArrayList();
//
//            //遍历查出来的数据，进行相关逻辑处理
//            for(Permission permission:permissionList){
//                //假定所有的节点都是子节点
//                //Permission child = permission;
//                //如果遍历的出的对象的pid值为null，则说明是根节点
//                if(permission.getPid() == null){
//                    root.add(permission);
//                }else{
//                    for(Permission innerChildren: permissionList){
//                        if(permission.getPid() == innerChildren.getId()){
//                            innerChildren.getChildren().add(permission);
//                            break;
//                        }
//                    }
//                }
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


//    /**
//     * Demo3 -- 数据库交互：递归调用
//     *
//     * 递归：
//     * 1.基准条件
//     * 2.范围逐渐缩小
//     * 3.调用自身函数
//     *
//     * 缺点：
//     * 1.多次查询数据库，数据库压力大
//     * 2.递归调用会在内存栈区压栈，容易造成内存溢出
//     *
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
//            queryChildrenPerminssion(parent);
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

//    /**
//     * 递归方法
//     * @param parent
//     */
//    private void queryChildrenPerminssion(Permission parent){
//        List<Permission> childrenList = permissionService.queryPermissionByParenetID(parent.getId());
//        parent.setChildren(childrenList);
//
//        //遍历子节点(foreach循环）
//        for (Permission children: childrenList) {
//            queryChildrenPerminssion(children);
//        }
//    }

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
