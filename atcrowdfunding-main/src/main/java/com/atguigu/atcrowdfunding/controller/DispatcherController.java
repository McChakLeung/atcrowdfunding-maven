package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.exception.LoginException;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DispatcherController {

    @Autowired
    private UserService userService;

    /**
     * 该方法接收webapp目录中index.jsp跳转过来的请求，其中的index.htm在控制器中可以被识别
     *
     * @return 跳转至真正的首页面index.jsp,该页面在springmvc-context.html中配置了InternalResourceViewResolver
     */
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 该方法接收webapp目录中index.jsp的登陆请求login.htm，该在控制器中可以被识别
     *
     * @return 跳转至登录页面
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 接收doLogin中的重定向请求，并调整至main.jsp页面
     *
     * @return main.jsp
     */
    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    /**
     * 以同步请求的方式登录：接收页面请求的参数(loginacct登录名，userpswd登录密码，type登录类型)，并传递给后台查询
     *
     * @return
     */
//    @RequestMapping("/doLogin")
//    public String doLogin(String loginacct, String userpswd, String type, HttpSession session){
//        //创建一个map来接收参数
//        Map<String,Object> params = new HashMap();
//        params.put("loginacct",loginacct);
//        params.put("userpswd",userpswd);
//        params.put("type",type);
//        User user = userService.selectUserByLoginAccAndUserPassword(params);
//        //创建一个Const工具类，存放常量
//        session.setAttribute(Const.LOGIN_USER,user);
//        return "redirect:/main.htm";
//    }

    /**
     * 以异步请求的方式登录：接收页面请求的参数(loginacct登录名，userpswd登录密码，type登录类型)，并传递给后台查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/doLogin")
    public Object doLogin(String loginacct, String userpswd, String type, HttpSession session){

        AjaxResult result = new AjaxResult();

        try {
            //创建一个map来接收参数
            Map<String,Object> params = new HashMap();
            params.put("loginacct",loginacct);
            params.put("userpswd",userpswd);
            params.put("type",type);
            User user = userService.selectUserByLoginAccAndUserPassword(params);
            //创建一个Const工具类，存放常量
            session.setAttribute(Const.LOGIN_USER,user);
            result.setSuccess(true);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("登录错误，请联系管理员处理！");
            e.printStackTrace();
        }
        return result;
    }
}
