package com.atguigu.atcrowdfunding.listener;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.StringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SystemLaunchListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public SystemLaunchListener() {
    }

    /*
    *  监听器初始化操作
    *
    */
    public void contextInitialized(ServletContextEvent sce) {
        //从Tomcat中获取SerletContext对象
        ServletContext application = sce.getServletContext();
        //获取contextpath项目路径
        String contextPath = application.getContextPath();
        //将项目路径存放到ServletContext对象中
        application.setAttribute("APP_PATH",contextPath);

        //从Tomcat中获得spring的IOC容器
        ApplicationContext ioc =  WebApplicationContextUtils.getWebApplicationContext(application);
        //从IOC容器中获取PermissionService对象
        PermissionService permissionService = ioc.getBean(PermissionService.class);
        List<Permission> permissionList = permissionService.selectAllPermission();
        Set<String> allPermissionUris = new HashSet<>();
        for (Permission permission : permissionList){
            if(StringUtil.isNotEmpty(permission.getUrl())){
                allPermissionUris.add("/"+permission.getUrl());
            }
        }
        application.setAttribute(Const.ALL_PERMISSION_URI,allPermissionUris);
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
