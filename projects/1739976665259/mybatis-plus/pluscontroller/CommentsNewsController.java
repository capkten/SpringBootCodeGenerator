package com.hzy.controller;
import com.alibaba.fastjson.JSON;
import com.hzy.entity.CommentsNews;
import com.hzy.mapper.CommentsNewsMapper;
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
* @description comments_news控制器
* @author capkin
* @date 2025-02-19
*/
@Slf4j
@RestController
@RequestMapping("/commentsNews")
public class CommentsNewsController {

    @Autowired
    private CommentsNewsMapper commentsNewsMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(@RequestBody CommentsNews commentsNews){
        log.info("commentsNews:"+JSON.toJSONString(commentsNews));
        CommentsNews oldCommentsNews = commentsNewsMapper.selectOne(new QueryWrapper<CommentsNews>().eq("commentsNews_id",commentsNews.getCommentsNewsId()));
        commentsNews.setUpdateTime(new Date());
        if(oldCommentsNews!=null){
            commentsNewsMapper.updateById(commentsNews);
        }else{
        if(commentsNewsMapper.selectOne(new QueryWrapper<CommentsNews>().eq("commentsNews_name",commentsNews.getCommentsNewsName()))!=null){
            return ReturnT.error("保存失败，名字重复");
        }
        commentsNews.setCreateTime(new Date());
        commentsNewsMapper.insert(commentsNews);
        }
        return ReturnT.success("保存成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
    CommentsNews commentsNews = commentsNewsMapper.selectOne(new QueryWrapper<CommentsNews>().eq("commentsNews_id",id));
        if(commentsNews!=null){
            commentsNewsMapper.deleteById(id);
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
    CommentsNews commentsNews = commentsNewsMapper.selectOne(new QueryWrapper<CommentsNews>().eq("commentsNews_id",id));
        if(commentsNews!=null){
            return ReturnT.success(commentsNews);
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
        Page<CommentsNews> buildPage = new Page<CommentsNews>(page,limit);
        //条件构造器
        QueryWrapper<CommentsNews> queryWrapper = new QueryWrapper<CommentsNews>();
        if(StringUtils.isNotEmpty(searchParams)&&JSON.isValid(searchParams)) {
            CommentsNews commentsNews = JSON.parseObject(searchParams, CommentsNews.class);
            queryWrapper.eq(StringUtils.isNoneEmpty(commentsNews.getCommentsNewsName()), "commentsNews_name", commentsNews.getCommentsNewsName());
        }
        //执行分页
        IPage<CommentsNews> pageList = commentsNewsMapper.selectPage(buildPage, queryWrapper);
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
        CommentsNews queryParamDTO = JSON.parseObject(searchParams, CommentsNews.class);
        //专用DTO模式
        //QueryParamDTO queryParamDTO = JSON.parseObject(searchParams, QueryParamDTO.class);
        //queryParamDTO.setPage((page - 1)* limit);
        //queryParamDTO.setLimit(limit);
        //(page - 1) * limit, limit
        List<CommentsNews> itemList = commentsNewsMapper.pageAll(queryParamDTO,(page - 1)* limit,limit);
        Integer itemCount = commentsNewsMapper.countAll(queryParamDTO);
        //返回结果
        return ReturnT.success.PAGE(itemList,itemCount);
    }*/
    @GetMapping("/list")
    public ModelAndView listPage(){
        return new ModelAndView("commentsNews-list");
    }

    @GetMapping("/edit")
    public ModelAndView editPage(int id){
        CommentsNews commentsNews = commentsNewsMapper.selectOne(new QueryWrapper<CommentsNews>().eq("commentsNews_id",id));
        return new ModelAndView("commentsNews-edit","commentsNews",commentsNews);
    }

    /**
    * 发布/暂停(如不需要请屏蔽)
    */
    @PostMapping("/publish")
    public Object publish(int id,Integer status){
        CommentsNews commentsNews = commentsNewsMapper.selectOne(new QueryWrapper<CommentsNews>().eq("commentsNews_id",id));
        if(commentsNews!=null){
            commentsNews.setUpdateTime(new Date());
            commentsNews.setStatus(status);
            commentsNewsMapper.updateById(commentsNews);
            return ReturnT.success((status==1)?"已发布":"已暂停");
        }else if(status.equals(commentsNews.getStatus())){
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



