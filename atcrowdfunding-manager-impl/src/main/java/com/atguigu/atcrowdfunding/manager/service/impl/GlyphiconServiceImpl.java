package com.atguigu.atcrowdfunding.manager.service.impl;

import com.atguigu.atcrowdfunding.bean.Glyphicon;
import com.atguigu.atcrowdfunding.manager.dao.GlyphiconMapper;
import com.atguigu.atcrowdfunding.manager.service.GlyphiconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlyphiconServiceImpl implements GlyphiconService {

    @Autowired
    private GlyphiconMapper glyphiconMapper;

    @Override
    public List<Glyphicon> queryAllGlyphicon() {
        return glyphiconMapper.queryAllGlyphicon();
    }
}
