package com.atguigu.atcrowdfunding.bean;

import java.util.List;

public class Permission {
    private Integer id;

    private Integer pid;

    private String name;

    private String icon;

    private String url;

    private List<Permission> childrenList;

    public List<Permission> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Permission> childrenList) {
        this.childrenList = childrenList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}