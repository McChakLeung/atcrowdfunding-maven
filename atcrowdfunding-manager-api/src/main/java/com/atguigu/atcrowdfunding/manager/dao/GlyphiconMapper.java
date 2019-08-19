package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.Glyphicon;

import java.util.List;

public interface GlyphiconMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Glyphicon record);

    int insertSelective(Glyphicon record);

    Glyphicon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Glyphicon record);

    int updateByPrimaryKey(Glyphicon record);

    List<Glyphicon> queryAllGlyphicon();
}