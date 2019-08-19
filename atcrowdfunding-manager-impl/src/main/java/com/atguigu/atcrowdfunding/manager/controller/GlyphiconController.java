package com.atguigu.atcrowdfunding.manager.controller;

import com.atguigu.atcrowdfunding.bean.Glyphicon;
import com.atguigu.atcrowdfunding.manager.service.GlyphiconService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GlyphiconController {

    @Autowired
    private GlyphiconService glyphiconService;

    @ResponseBody
    @RequestMapping("/loadIcon")
    public Object loadIcon(){
        AjaxResult result = new AjaxResult();
        try{
            List<Glyphicon> glyphiconList = glyphiconService.queryAllGlyphicon();
            result.setDatas(glyphiconList);
            result.setSuccess(glyphiconList.size()>0);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("加载图标失败");
        }
        return result;

    }
}
