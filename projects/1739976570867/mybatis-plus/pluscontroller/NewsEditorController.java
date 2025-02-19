package com.hzy.controller;
import com.alibaba.fastjson.JSON;
import com.hzy.entity.NewsEditor;
import com.hzy.mapper.NewsEditorMapper;
import com.hzy.util.ReturnT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;
/**
* @description news_editor控制器
* @author capkin
* @date 2025-02-19
*/
@Slf4j
@RestController
@RequestMapping("/newsEditor")
public class NewsEditorController {

    @Autowired
    private NewsEditorMapper newsEditorMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(@RequestBody NewsEditor newsEditor){
        log.info("newsEditor:"+JSON.toJSONString(newsEditor));
        NewsEditor oldNewsEditor = newsEditorMapper.selectOne(new QueryWrapper<NewsEditor>().eq("newsEditor_id",newsEditor.getNewsEditorId()));
        newsEditor.setUpdateTime(new Date());
        if(oldNewsEditor!=null){
            newsEditorMapper.updateById(newsEditor);
        }else{
        if(newsEditorMapper.selectOne(new QueryWrapper<NewsEditor>().eq("newsEditor_name",newsEditor.getNewsEditorName()))!=null){
            return ReturnT.error("保存失败，名字重复");
        }
        newsEditor.setCreateTime(new Date());
        newsEditorMapper.insert(newsEditor);
        }
        return ReturnT.success("保存成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
    NewsEditor newsEditor = newsEditorMapper.selectOne(new QueryWrapper<NewsEditor>().eq("newsEditor_id",id));
        if(newsEditor!=null){
            newsEditorMapper.deleteById(id);
            return ReturnT.success("删除成功");
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 查询
    */
    @PostMapping("/find")
    public Object find(int id){
    NewsEditor newsEditor = newsEditorMapper.selectOne(new QueryWrapper<NewsEditor>().eq("newsEditor_id",id));
        if(newsEditor!=null){
            return ReturnT.success(newsEditor);
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 自动分页查询
    */
    @PostMapping("/list")
    public Object list(String searchParams,
    @RequestParam(required = false, defaultValue = "0") int page,
    @RequestParam(required = false, defaultValue = "10") int limit) {
        log.info("page:"+page+"-limit:"+limit+"-json:"+ JSON.toJSONString(searchParams));
        //分页构造器
        Page<NewsEditor> buildPage = new Page<NewsEditor>(page,limit);
        //条件构造器
        QueryWrapper<NewsEditor> queryWrapper = new QueryWrapper<NewsEditor>();
        if(StringUtils.isNotEmpty(searchParams)&&JSON.isValid(searchParams)) {
            NewsEditor newsEditor = JSON.parseObject(searchParams, NewsEditor.class);
            queryWrapper.eq(StringUtils.isNoneEmpty(newsEditor.getNewsEditorName()), "newsEditor_name", newsEditor.getNewsEditorName());
        }
        //执行分页
        IPage<NewsEditor> pageList = newsEditorMapper.selectPage(buildPage, queryWrapper);
        //返回结果
        return .PAGE(pageList.getRecords(),pageList.getTotal());
    }
    /**
    * 手工分页查询(按需使用)
    */
    /*@PostMapping("/list2")
    public Object list2(String searchParams,
    @RequestParam(required = false, defaultValue = "0") int page,
    @RequestParam(required = false, defaultValue = "10") int limit) {
        log.info("searchParams:"+ JSON.toJSONString(searchParams));
        //通用模式
        NewsEditor queryParamDTO = JSON.parseObject(searchParams, NewsEditor.class);
        //专用DTO模式
        //QueryParamDTO queryParamDTO = JSON.parseObject(searchParams, QueryParamDTO.class);
        //queryParamDTO.setPage((page - 1)* limit);
        //queryParamDTO.setLimit(limit);
        //(page - 1) * limit, limit
        List<NewsEditor> itemList = newsEditorMapper.pageAll(queryParamDTO,(page - 1)* limit,limit);
        Integer itemCount = newsEditorMapper.countAll(queryParamDTO);
        //返回结果
        return ReturnT.success.PAGE(itemList,itemCount);
    }*/
    @GetMapping("/list")
    public ModelAndView listPage(){
        return new ModelAndView("newsEditor-list");
    }

    @GetMapping("/edit")
    public ModelAndView editPage(int id){
        NewsEditor newsEditor = newsEditorMapper.selectOne(new QueryWrapper<NewsEditor>().eq("newsEditor_id",id));
        return new ModelAndView("newsEditor-edit","newsEditor",newsEditor);
    }

    /**
    * 发布/暂停(如不需要请屏蔽)
    */
    @PostMapping("/publish")
    public Object publish(int id,Integer status){
        NewsEditor newsEditor = newsEditorMapper.selectOne(new QueryWrapper<NewsEditor>().eq("newsEditor_id",id));
        if(newsEditor!=null){
            newsEditor.setUpdateTime(new Date());
            newsEditor.setStatus(status);
            newsEditorMapper.updateById(newsEditor);
            return ReturnT.success((status==1)?"已发布":"已暂停");
        }else if(status.equals(newsEditor.getStatus())){
            return ReturnT.error("状态不正确");
        }else{
            return ReturnT.error();
        }
    }

    /**
    * 执行(如不需要请屏蔽)
    */
    @PostMapping("/execute")
    public Object execute(){
        return ReturnT.success;
    }
}
}



