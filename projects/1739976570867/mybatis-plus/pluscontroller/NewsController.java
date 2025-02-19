package com.hzy.controller;
import com.alibaba.fastjson.JSON;
import com.hzy.entity.News;
import com.hzy.mapper.NewsMapper;
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
* @description news控制器
* @author capkin
* @date 2025-02-19
*/
@Slf4j
@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsMapper newsMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(@RequestBody News news){
        log.info("news:"+JSON.toJSONString(news));
        News oldNews = newsMapper.selectOne(new QueryWrapper<News>().eq("news_id",news.getNewsId()));
        news.setUpdateTime(new Date());
        if(oldNews!=null){
            newsMapper.updateById(news);
        }else{
        if(newsMapper.selectOne(new QueryWrapper<News>().eq("news_name",news.getNewsName()))!=null){
            return ReturnT.error("保存失败，名字重复");
        }
        news.setCreateTime(new Date());
        newsMapper.insert(news);
        }
        return ReturnT.success("保存成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
    News news = newsMapper.selectOne(new QueryWrapper<News>().eq("news_id",id));
        if(news!=null){
            newsMapper.deleteById(id);
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
    News news = newsMapper.selectOne(new QueryWrapper<News>().eq("news_id",id));
        if(news!=null){
            return ReturnT.success(news);
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
        Page<News> buildPage = new Page<News>(page,limit);
        //条件构造器
        QueryWrapper<News> queryWrapper = new QueryWrapper<News>();
        if(StringUtils.isNotEmpty(searchParams)&&JSON.isValid(searchParams)) {
            News news = JSON.parseObject(searchParams, News.class);
            queryWrapper.eq(StringUtils.isNoneEmpty(news.getNewsName()), "news_name", news.getNewsName());
        }
        //执行分页
        IPage<News> pageList = newsMapper.selectPage(buildPage, queryWrapper);
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
        News queryParamDTO = JSON.parseObject(searchParams, News.class);
        //专用DTO模式
        //QueryParamDTO queryParamDTO = JSON.parseObject(searchParams, QueryParamDTO.class);
        //queryParamDTO.setPage((page - 1)* limit);
        //queryParamDTO.setLimit(limit);
        //(page - 1) * limit, limit
        List<News> itemList = newsMapper.pageAll(queryParamDTO,(page - 1)* limit,limit);
        Integer itemCount = newsMapper.countAll(queryParamDTO);
        //返回结果
        return ReturnT.success.PAGE(itemList,itemCount);
    }*/
    @GetMapping("/list")
    public ModelAndView listPage(){
        return new ModelAndView("news-list");
    }

    @GetMapping("/edit")
    public ModelAndView editPage(int id){
        News news = newsMapper.selectOne(new QueryWrapper<News>().eq("news_id",id));
        return new ModelAndView("news-edit","news",news);
    }

    /**
    * 发布/暂停(如不需要请屏蔽)
    */
    @PostMapping("/publish")
    public Object publish(int id,Integer status){
        News news = newsMapper.selectOne(new QueryWrapper<News>().eq("news_id",id));
        if(news!=null){
            news.setUpdateTime(new Date());
            news.setStatus(status);
            newsMapper.updateById(news);
            return ReturnT.success((status==1)?"已发布":"已暂停");
        }else if(status.equals(news.getStatus())){
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



