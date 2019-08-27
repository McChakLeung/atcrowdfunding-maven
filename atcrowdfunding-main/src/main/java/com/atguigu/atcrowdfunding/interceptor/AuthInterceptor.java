package com.atguigu.atcrowdfunding.interceptor;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.util.Const;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取用户要访问的路径
        String requestPath = request.getServletPath();

        //获取session
        HttpSession session = request.getSession();

        //获取session中存放的当前用户权限列表
        Set<String> myUrisList = (Set<String>)session.getAttribute(Const.MY_URIS);

        //获取ServletContext中的数据
        ServletContext application = session.getServletContext();
        Set<String> allPermissionUris = (Set<String>) application.getAttribute(Const.ALL_PERMISSION_URI);

        //判断该路径是否在数据库中
        //如果该访问路径不在数据库的访问路径中，则放行；否则进行下一步判断
        if(allPermissionUris.contains(requestPath)){
            //如果在，则判断是否在用户所拥有的权限列表中
            if(myUrisList.contains(requestPath)){
                //如果在，则放行
                return true;
            }else{
                //如果不在，则跳转到提示页面
                response.sendRedirect(request.getContextPath() + "/auth.do" );
                return false;
            }
        }else{
            return true;
        }

    }
}
