package com.hzy.controller;
import com.alibaba.fastjson.JSON;
import com.hzy.entity.LikesNews;
import com.hzy.mapper.LikesNewsMapper;
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
* @description likes_news控制器
* @author capkin
* @date 2025-02-19
*/
@Slf4j
@RestController
@RequestMapping("/likesNews")
public class LikesNewsController {

    @Autowired
    private LikesNewsMapper likesNewsMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(@RequestBody LikesNews likesNews){
        log.info("likesNews:"+JSON.toJSONString(likesNews));
        LikesNews oldLikesNews = likesNewsMapper.selectOne(new QueryWrapper<LikesNews>().eq("likesNews_id",likesNews.getLikesNewsId()));
        likesNews.setUpdateTime(new Date());
        if(oldLikesNews!=null){
            likesNewsMapper.updateById(likesNews);
        }else{
        if(likesNewsMapper.selectOne(new QueryWrapper<LikesNews>().eq("likesNews_name",likesNews.getLikesNewsName()))!=null){
            return ReturnT.error("保存失败，名字重复");
        }
        likesNews.setCreateTime(new Date());
        likesNewsMapper.insert(likesNews);
        }
        return ReturnT.success("保存成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
    LikesNews likesNews = likesNewsMapper.selectOne(new QueryWrapper<LikesNews>().eq("likesNews_id",id));
        if(likesNews!=null){
            likesNewsMapper.deleteById(id);
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
    LikesNews likesNews = likesNewsMapper.selectOne(new QueryWrapper<LikesNews>().eq("likesNews_id",id));
        if(likesNews!=null){
            return ReturnT.success(likesNews);
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
        Page<LikesNews> buildPage = new Page<LikesNews>(page,limit);
        //条件构造器
        QueryWrapper<LikesNews> queryWrapper = new QueryWrapper<LikesNews>();
        if(StringUtils.isNotEmpty(searchParams)&&JSON.isValid(searchParams)) {
            LikesNews likesNews = JSON.parseObject(searchParams, LikesNews.class);
            queryWrapper.eq(StringUtils.isNoneEmpty(likesNews.getLikesNewsName()), "likesNews_name", likesNews.getLikesNewsName());
        }
        //执行分页
        IPage<LikesNews> pageList = likesNewsMapper.selectPage(buildPage, queryWrapper);
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
        LikesNews queryParamDTO = JSON.parseObject(searchParams, LikesNews.class);
        //专用DTO模式
        //QueryParamDTO queryParamDTO = JSON.parseObject(searchParams, QueryParamDTO.class);
        //queryParamDTO.setPage((page - 1)* limit);
        //queryParamDTO.setLimit(limit);
        //(page - 1) * limit, limit
        List<LikesNews> itemList = likesNewsMapper.pageAll(queryParamDTO,(page - 1)* limit,limit);
        Integer itemCount = likesNewsMapper.countAll(queryParamDTO);
        //返回结果
        return ReturnT.success.PAGE(itemList,itemCount);
    }*/
    @GetMapping("/list")
    public ModelAndView listPage(){
        return new ModelAndView("likesNews-list");
    }

    @GetMapping("/edit")
    public ModelAndView editPage(int id){
        LikesNews likesNews = likesNewsMapper.selectOne(new QueryWrapper<LikesNews>().eq("likesNews_id",id));
        return new ModelAndView("likesNews-edit","likesNews",likesNews);
    }

    /**
    * 发布/暂停(如不需要请屏蔽)
    */
    @PostMapping("/publish")
    public Object publish(int id,Integer status){
        LikesNews likesNews = likesNewsMapper.selectOne(new QueryWrapper<LikesNews>().eq("likesNews_id",id));
        if(likesNews!=null){
            likesNews.setUpdateTime(new Date());
            likesNews.setStatus(status);
            likesNewsMapper.updateById(likesNews);
            return ReturnT.success((status==1)?"已发布":"已暂停");
        }else if(status.equals(likesNews.getStatus())){
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



