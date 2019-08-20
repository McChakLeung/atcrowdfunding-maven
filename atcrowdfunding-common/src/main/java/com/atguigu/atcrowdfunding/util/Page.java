package com.atguigu.atcrowdfunding.util;

import java.util.List;

public class Page<Object> {

    private List datalist;

    private Integer totalno;

    private Integer pageno;

    private Integer totalsize;

    private Integer pagesize;

    //定义一个带参构造方法，用于传入一个默认值并进行判断
    public Page(Integer pageno, Integer pagesize) {
        if(pageno <= 0){
            this.pageno = 1;
        }else{
            this.pageno = pageno;
        }

        if(pagesize <= 0){
            this.pagesize = 1;
        }else{
            this.pagesize = pagesize;
        }
    }

    public List getDatalist() {
        return datalist;
    }

    public void setDatalist(List datalist) {
        this.datalist = datalist;
    }

    public Integer getTotalno() {
        return totalno;
    }

    private void setTotalno(Integer totalno) {
        this.totalno = totalno;
    }

    public Integer getPageno() {
        return pageno;
    }

    public void setPageno(Integer pageno) {
        this.pageno = pageno;
    }

    public Integer getTotalsize() {
        return totalsize;
    }

    //总页数需要通过总记录数与每页记录数进行模运算
    public void setTotalsize(Integer totalsize) {
        this.totalsize = totalsize;
        this.totalno = (totalsize%pagesize)==0?(totalsize/pagesize):(totalsize/pagesize+1);
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public Integer getStartline(){
        return (this.pageno-1) * this.pagesize;
    }
}
