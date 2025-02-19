package com.hzy.controller;
import com.alibaba.fastjson.JSON;
import com.hzy.entity.NewsTeam;
import com.hzy.mapper.NewsTeamMapper;
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
* @description news_team控制器
* @author capkin
* @date 2025-02-19
*/
@Slf4j
@RestController
@RequestMapping("/newsTeam")
public class NewsTeamController {

    @Autowired
    private NewsTeamMapper newsTeamMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(@RequestBody NewsTeam newsTeam){
        log.info("newsTeam:"+JSON.toJSONString(newsTeam));
        NewsTeam oldNewsTeam = newsTeamMapper.selectOne(new QueryWrapper<NewsTeam>().eq("newsTeam_id",newsTeam.getNewsTeamId()));
        newsTeam.setUpdateTime(new Date());
        if(oldNewsTeam!=null){
            newsTeamMapper.updateById(newsTeam);
        }else{
        if(newsTeamMapper.selectOne(new QueryWrapper<NewsTeam>().eq("newsTeam_name",newsTeam.getNewsTeamName()))!=null){
            return ReturnT.error("保存失败，名字重复");
        }
        newsTeam.setCreateTime(new Date());
        newsTeamMapper.insert(newsTeam);
        }
        return ReturnT.success("保存成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
    NewsTeam newsTeam = newsTeamMapper.selectOne(new QueryWrapper<NewsTeam>().eq("newsTeam_id",id));
        if(newsTeam!=null){
            newsTeamMapper.deleteById(id);
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
    NewsTeam newsTeam = newsTeamMapper.selectOne(new QueryWrapper<NewsTeam>().eq("newsTeam_id",id));
        if(newsTeam!=null){
            return ReturnT.success(newsTeam);
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
        Page<NewsTeam> buildPage = new Page<NewsTeam>(page,limit);
        //条件构造器
        QueryWrapper<NewsTeam> queryWrapper = new QueryWrapper<NewsTeam>();
        if(StringUtils.isNotEmpty(searchParams)&&JSON.isValid(searchParams)) {
            NewsTeam newsTeam = JSON.parseObject(searchParams, NewsTeam.class);
            queryWrapper.eq(StringUtils.isNoneEmpty(newsTeam.getNewsTeamName()), "newsTeam_name", newsTeam.getNewsTeamName());
        }
        //执行分页
        IPage<NewsTeam> pageList = newsTeamMapper.selectPage(buildPage, queryWrapper);
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
        NewsTeam queryParamDTO = JSON.parseObject(searchParams, NewsTeam.class);
        //专用DTO模式
        //QueryParamDTO queryParamDTO = JSON.parseObject(searchParams, QueryParamDTO.class);
        //queryParamDTO.setPage((page - 1)* limit);
        //queryParamDTO.setLimit(limit);
        //(page - 1) * limit, limit
        List<NewsTeam> itemList = newsTeamMapper.pageAll(queryParamDTO,(page - 1)* limit,limit);
        Integer itemCount = newsTeamMapper.countAll(queryParamDTO);
        //返回结果
        return ReturnT.success.PAGE(itemList,itemCount);
    }*/
    @GetMapping("/list")
    public ModelAndView listPage(){
        return new ModelAndView("newsTeam-list");
    }

    @GetMapping("/edit")
    public ModelAndView editPage(int id){
        NewsTeam newsTeam = newsTeamMapper.selectOne(new QueryWrapper<NewsTeam>().eq("newsTeam_id",id));
        return new ModelAndView("newsTeam-edit","newsTeam",newsTeam);
    }

    /**
    * 发布/暂停(如不需要请屏蔽)
    */
    @PostMapping("/publish")
    public Object publish(int id,Integer status){
        NewsTeam newsTeam = newsTeamMapper.selectOne(new QueryWrapper<NewsTeam>().eq("newsTeam_id",id));
        if(newsTeam!=null){
            newsTeam.setUpdateTime(new Date());
            newsTeam.setStatus(status);
            newsTeamMapper.updateById(newsTeam);
            return ReturnT.success((status==1)?"已发布":"已暂停");
        }else if(status.equals(newsTeam.getStatus())){
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



