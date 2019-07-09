package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.ParamExample;

import java.util.List;

public interface ParamMapper {
    long countByExample(ParamExample example);

    int deleteByExample(ParamExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(com.atguigu.atcrowdfunding.bean.Param record);

    int insertSelective(com.atguigu.atcrowdfunding.bean.Param record);

    List<com.atguigu.atcrowdfunding.bean.Param> selectByExample(ParamExample example);

    com.atguigu.atcrowdfunding.bean.Param selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@com.atguigu.atcrowdfunding.bean.Param("record") com.atguigu.atcrowdfunding.bean.Param record, @com.atguigu.atcrowdfunding.bean.Param("example") ParamExample example);

    int updateByExample(@com.atguigu.atcrowdfunding.bean.Param("record") com.atguigu.atcrowdfunding.bean.Param record, @com.atguigu.atcrowdfunding.bean.Param("example") ParamExample example);

    int updateByPrimaryKeySelective(com.atguigu.atcrowdfunding.bean.Param record);

    int updateByPrimaryKey(com.atguigu.atcrowdfunding.bean.Param record);
}