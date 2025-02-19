package com.hzy.controller;
import com.alibaba.fastjson.JSON;
import com.hzy.entity.NewsLeague;
import com.hzy.mapper.NewsLeagueMapper;
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
* @description news_league控制器
* @author capkin
* @date 2025-02-19
*/
@Slf4j
@RestController
@RequestMapping("/newsLeague")
public class NewsLeagueController {

    @Autowired
    private NewsLeagueMapper newsLeagueMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(@RequestBody NewsLeague newsLeague){
        log.info("newsLeague:"+JSON.toJSONString(newsLeague));
        NewsLeague oldNewsLeague = newsLeagueMapper.selectOne(new QueryWrapper<NewsLeague>().eq("newsLeague_id",newsLeague.getNewsLeagueId()));
        newsLeague.setUpdateTime(new Date());
        if(oldNewsLeague!=null){
            newsLeagueMapper.updateById(newsLeague);
        }else{
        if(newsLeagueMapper.selectOne(new QueryWrapper<NewsLeague>().eq("newsLeague_name",newsLeague.getNewsLeagueName()))!=null){
            return ReturnT.error("保存失败，名字重复");
        }
        newsLeague.setCreateTime(new Date());
        newsLeagueMapper.insert(newsLeague);
        }
        return ReturnT.success("保存成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
    NewsLeague newsLeague = newsLeagueMapper.selectOne(new QueryWrapper<NewsLeague>().eq("newsLeague_id",id));
        if(newsLeague!=null){
            newsLeagueMapper.deleteById(id);
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
    NewsLeague newsLeague = newsLeagueMapper.selectOne(new QueryWrapper<NewsLeague>().eq("newsLeague_id",id));
        if(newsLeague!=null){
            return ReturnT.success(newsLeague);
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
        Page<NewsLeague> buildPage = new Page<NewsLeague>(page,limit);
        //条件构造器
        QueryWrapper<NewsLeague> queryWrapper = new QueryWrapper<NewsLeague>();
        if(StringUtils.isNotEmpty(searchParams)&&JSON.isValid(searchParams)) {
            NewsLeague newsLeague = JSON.parseObject(searchParams, NewsLeague.class);
            queryWrapper.eq(StringUtils.isNoneEmpty(newsLeague.getNewsLeagueName()), "newsLeague_name", newsLeague.getNewsLeagueName());
        }
        //执行分页
        IPage<NewsLeague> pageList = newsLeagueMapper.selectPage(buildPage, queryWrapper);
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
        NewsLeague queryParamDTO = JSON.parseObject(searchParams, NewsLeague.class);
        //专用DTO模式
        //QueryParamDTO queryParamDTO = JSON.parseObject(searchParams, QueryParamDTO.class);
        //queryParamDTO.setPage((page - 1)* limit);
        //queryParamDTO.setLimit(limit);
        //(page - 1) * limit, limit
        List<NewsLeague> itemList = newsLeagueMapper.pageAll(queryParamDTO,(page - 1)* limit,limit);
        Integer itemCount = newsLeagueMapper.countAll(queryParamDTO);
        //返回结果
        return ReturnT.success.PAGE(itemList,itemCount);
    }*/
    @GetMapping("/list")
    public ModelAndView listPage(){
        return new ModelAndView("newsLeague-list");
    }

    @GetMapping("/edit")
    public ModelAndView editPage(int id){
        NewsLeague newsLeague = newsLeagueMapper.selectOne(new QueryWrapper<NewsLeague>().eq("newsLeague_id",id));
        return new ModelAndView("newsLeague-edit","newsLeague",newsLeague);
    }

    /**
    * 发布/暂停(如不需要请屏蔽)
    */
    @PostMapping("/publish")
    public Object publish(int id,Integer status){
        NewsLeague newsLeague = newsLeagueMapper.selectOne(new QueryWrapper<NewsLeague>().eq("newsLeague_id",id));
        if(newsLeague!=null){
            newsLeague.setUpdateTime(new Date());
            newsLeague.setStatus(status);
            newsLeagueMapper.updateById(newsLeague);
            return ReturnT.success((status==1)?"已发布":"已暂停");
        }else if(status.equals(newsLeague.getStatus())){
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



