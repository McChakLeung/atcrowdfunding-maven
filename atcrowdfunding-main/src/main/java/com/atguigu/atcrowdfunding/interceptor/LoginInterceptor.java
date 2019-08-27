package com.atguigu.atcrowdfunding.interceptor;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.util.Const;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

public class LoginInterceptor extends HandlerInterceptorAdapter{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //设置访问白名单并放在set集合中
        Set<String> whiteList = new HashSet<>();
        whiteList.add("/index.htm");
        whiteList.add("/login.htm");
        whiteList.add("/preLogin.do");
        whiteList.add("/doLogin.do");
        whiteList.add("/logout.do");
        whiteList.add("/reg.do");
        whiteList.add("/reg.htm");

        //获取当前用户访问的路径
        String requestPath = request.getServletPath();
        if(whiteList.contains(requestPath)){
            return true;
        }
        //从session中获取登陆用户是否存在
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Const.LOGIN_USER);
        if(user != null){
            return true;
        }else{
            response.sendRedirect(request.getContextPath() + "/login.htm");
            return false;
        }
    }
}
