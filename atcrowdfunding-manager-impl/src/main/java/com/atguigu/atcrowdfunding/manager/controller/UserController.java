package com.atguigu.atcrowdfunding.manager.controller;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.Page;
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
                        Map<String,Object> map){

        AjaxResult result = new AjaxResult();

        try{
            Map<String,Object> params = new HashMap<>();
            params.put("pageno",pageno);
            params.put("pagesize",pagesize);
//        params.put("queryText",queryText);
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

}
