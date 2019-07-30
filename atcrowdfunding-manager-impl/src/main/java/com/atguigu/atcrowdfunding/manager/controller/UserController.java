package com.atguigu.atcrowdfunding.manager.controller;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    /**
//     * 以同步方式查询用户页面数据，并将结果集放在一个自定义的page类中，该类封装成分页类
//     * @param pageno 查询的当前页
//     * @param pagesize  查询的每页包含的记录
//     * @return 返回查询页面
//     */
//    @RequestMapping("/toIndex")
//    public String toIndex(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
//                        @RequestParam(value = "pagesize",required = false,defaultValue = "10") Integer pagesize,
//                        Map<String,Object> map){
//
//        Map<String,Object> params = new HashMap<>();
//        params.put("pageno",pageno);
//        params.put("pagesize",pagesize);
////        params.put("queryText",queryText);
//        Page<User> page = userService.selectUserList(params);
//        map.put(Const.PAGE,page);
//        return "user/index";
//    }

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "user/index";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "user/add";
    }

    /**
     * 接收update前台的数据，根据id查询user，并通过map传回到前端
     * @param id 用户id
     * @param map  返回前台数据
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Map<String,Object> map) {
        User user = userService.selectUserByID(id);
        map.put("user",user);
        return "user/update";
    }

    /**
     * 以异步的方式查询用户页面数据，并将结果集放在一个自定义的page类中，该类封装成分页类
     * @param pageno 查询的当前页
     * @param pagesize  查询的每页包含的记录
     * @return 返回查询页面
     */
    @ResponseBody
    @RequestMapping("/index")
    public Object toIndex(@RequestParam(value = "pageno",required = false,defaultValue = "1") Integer pageno,
                        @RequestParam(value = "pagesize",required = false,defaultValue = "10") Integer pagesize,
                        String queryText){

        AjaxResult result = new AjaxResult();

        try{
            Map<String,Object> params = new HashMap<>();
            params.put("pageno",pageno);
            params.put("pagesize",pagesize);
            if(StringUtil.isNotEmpty(queryText)){
                if(queryText.contains("%")){
                    queryText = queryText.replaceAll("%", "\\\\%");
                }
                params.put("queryText", queryText); //   \%
            }
            Page<User> page = userService.selectUserList(params);
            if(page == null){
                result.setSuccess(false);
                result.setMessage("无相关记录，请更换关键字后再查询");
                return result;
            }
            result.setPage(page);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("查询异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 执行添加
     * @return 返回查询结果集
     */
    @ResponseBody
    @RequestMapping("/doAdd")
    public Object doAdd(User user){

        AjaxResult result = new AjaxResult();

        try{
            Integer count = userService.saveUser(user);
            if(count == 0){
                result.setSuccess(false);
                result.setMessage("保存数据失败，请重新尝试");
                return result;
            }
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("保存异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 执行更新
     * @return 返回查询结果集
     */
    @ResponseBody
    @RequestMapping("/doUpdate")
    public Object doUpdate(User user){

        AjaxResult result = new AjaxResult();

        try{
            Integer count = userService.updateUser(user);
            if(count == 0){
                result.setSuccess(false);
                result.setMessage("保存数据失败，请重新尝试");
                return result;
            }
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("保存异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 单条记录删除
     * @return 返回查询结果集
     */
    @ResponseBody
    @RequestMapping("/doDelete")
    public Object doDelete(Integer id){

        AjaxResult result = new AjaxResult();

        try{
            Integer count = userService.deleteUserById(id);
            if(count == 0){
                result.setSuccess(false);
                result.setMessage("删除数据失败，请重新尝试");
                return result;
            }
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("删除异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量删除
     * @return 返回查询结果集
     */
    @ResponseBody
    @RequestMapping("/doDeleteBatch")
    public Object doDeleteBatch(Integer[] id){

        AjaxResult result = new AjaxResult();

        try{
            Integer count = userService.deleteUserByBatch(id);
            result.setSuccess(count==id.length);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("删除异常，请联系管理员处理");
            e.printStackTrace();
        }
        return result;
    }

}
