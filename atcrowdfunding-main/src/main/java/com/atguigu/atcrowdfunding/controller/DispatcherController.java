package com.atguigu.atcrowdfunding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DispatcherController {

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
}
