package com.hzy.controller;
import com.alibaba.fastjson.JSON;
import com.hzy.entity.PostsLeague;
import com.hzy.mapper.PostsLeagueMapper;
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
* @description posts_league控制器
* @author capkin
* @date 2025-02-19
*/
@Slf4j
@RestController
@RequestMapping("/postsLeague")
public class PostsLeagueController {

    @Autowired
    private PostsLeagueMapper postsLeagueMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(@RequestBody PostsLeague postsLeague){
        log.info("postsLeague:"+JSON.toJSONString(postsLeague));
        PostsLeague oldPostsLeague = postsLeagueMapper.selectOne(new QueryWrapper<PostsLeague>().eq("postsLeague_id",postsLeague.getPostsLeagueId()));
        postsLeague.setUpdateTime(new Date());
        if(oldPostsLeague!=null){
            postsLeagueMapper.updateById(postsLeague);
        }else{
        if(postsLeagueMapper.selectOne(new QueryWrapper<PostsLeague>().eq("postsLeague_name",postsLeague.getPostsLeagueName()))!=null){
            return ReturnT.error("保存失败，名字重复");
        }
        postsLeague.setCreateTime(new Date());
        postsLeagueMapper.insert(postsLeague);
        }
        return ReturnT.success("保存成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
    PostsLeague postsLeague = postsLeagueMapper.selectOne(new QueryWrapper<PostsLeague>().eq("postsLeague_id",id));
        if(postsLeague!=null){
            postsLeagueMapper.deleteById(id);
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
    PostsLeague postsLeague = postsLeagueMapper.selectOne(new QueryWrapper<PostsLeague>().eq("postsLeague_id",id));
        if(postsLeague!=null){
            return ReturnT.success(postsLeague);
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
        Page<PostsLeague> buildPage = new Page<PostsLeague>(page,limit);
        //条件构造器
        QueryWrapper<PostsLeague> queryWrapper = new QueryWrapper<PostsLeague>();
        if(StringUtils.isNotEmpty(searchParams)&&JSON.isValid(searchParams)) {
            PostsLeague postsLeague = JSON.parseObject(searchParams, PostsLeague.class);
            queryWrapper.eq(StringUtils.isNoneEmpty(postsLeague.getPostsLeagueName()), "postsLeague_name", postsLeague.getPostsLeagueName());
        }
        //执行分页
        IPage<PostsLeague> pageList = postsLeagueMapper.selectPage(buildPage, queryWrapper);
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
        PostsLeague queryParamDTO = JSON.parseObject(searchParams, PostsLeague.class);
        //专用DTO模式
        //QueryParamDTO queryParamDTO = JSON.parseObject(searchParams, QueryParamDTO.class);
        //queryParamDTO.setPage((page - 1)* limit);
        //queryParamDTO.setLimit(limit);
        //(page - 1) * limit, limit
        List<PostsLeague> itemList = postsLeagueMapper.pageAll(queryParamDTO,(page - 1)* limit,limit);
        Integer itemCount = postsLeagueMapper.countAll(queryParamDTO);
        //返回结果
        return ReturnT.success.PAGE(itemList,itemCount);
    }*/
    @GetMapping("/list")
    public ModelAndView listPage(){
        return new ModelAndView("postsLeague-list");
    }

    @GetMapping("/edit")
    public ModelAndView editPage(int id){
        PostsLeague postsLeague = postsLeagueMapper.selectOne(new QueryWrapper<PostsLeague>().eq("postsLeague_id",id));
        return new ModelAndView("postsLeague-edit","postsLeague",postsLeague);
    }

    /**
    * 发布/暂停(如不需要请屏蔽)
    */
    @PostMapping("/publish")
    public Object publish(int id,Integer status){
        PostsLeague postsLeague = postsLeagueMapper.selectOne(new QueryWrapper<PostsLeague>().eq("postsLeague_id",id));
        if(postsLeague!=null){
            postsLeague.setUpdateTime(new Date());
            postsLeague.setStatus(status);
            postsLeagueMapper.updateById(postsLeague);
            return ReturnT.success((status==1)?"已发布":"已暂停");
        }else if(status.equals(postsLeague.getStatus())){
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



