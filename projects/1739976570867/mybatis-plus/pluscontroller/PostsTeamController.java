package com.hzy.controller;
import com.alibaba.fastjson.JSON;
import com.hzy.entity.PostsTeam;
import com.hzy.mapper.PostsTeamMapper;
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
* @description posts_team控制器
* @author capkin
* @date 2025-02-19
*/
@Slf4j
@RestController
@RequestMapping("/postsTeam")
public class PostsTeamController {

    @Autowired
    private PostsTeamMapper postsTeamMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(@RequestBody PostsTeam postsTeam){
        log.info("postsTeam:"+JSON.toJSONString(postsTeam));
        PostsTeam oldPostsTeam = postsTeamMapper.selectOne(new QueryWrapper<PostsTeam>().eq("postsTeam_id",postsTeam.getPostsTeamId()));
        postsTeam.setUpdateTime(new Date());
        if(oldPostsTeam!=null){
            postsTeamMapper.updateById(postsTeam);
        }else{
        if(postsTeamMapper.selectOne(new QueryWrapper<PostsTeam>().eq("postsTeam_name",postsTeam.getPostsTeamName()))!=null){
            return ReturnT.error("保存失败，名字重复");
        }
        postsTeam.setCreateTime(new Date());
        postsTeamMapper.insert(postsTeam);
        }
        return ReturnT.success("保存成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
    PostsTeam postsTeam = postsTeamMapper.selectOne(new QueryWrapper<PostsTeam>().eq("postsTeam_id",id));
        if(postsTeam!=null){
            postsTeamMapper.deleteById(id);
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
    PostsTeam postsTeam = postsTeamMapper.selectOne(new QueryWrapper<PostsTeam>().eq("postsTeam_id",id));
        if(postsTeam!=null){
            return ReturnT.success(postsTeam);
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
        Page<PostsTeam> buildPage = new Page<PostsTeam>(page,limit);
        //条件构造器
        QueryWrapper<PostsTeam> queryWrapper = new QueryWrapper<PostsTeam>();
        if(StringUtils.isNotEmpty(searchParams)&&JSON.isValid(searchParams)) {
            PostsTeam postsTeam = JSON.parseObject(searchParams, PostsTeam.class);
            queryWrapper.eq(StringUtils.isNoneEmpty(postsTeam.getPostsTeamName()), "postsTeam_name", postsTeam.getPostsTeamName());
        }
        //执行分页
        IPage<PostsTeam> pageList = postsTeamMapper.selectPage(buildPage, queryWrapper);
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
        PostsTeam queryParamDTO = JSON.parseObject(searchParams, PostsTeam.class);
        //专用DTO模式
        //QueryParamDTO queryParamDTO = JSON.parseObject(searchParams, QueryParamDTO.class);
        //queryParamDTO.setPage((page - 1)* limit);
        //queryParamDTO.setLimit(limit);
        //(page - 1) * limit, limit
        List<PostsTeam> itemList = postsTeamMapper.pageAll(queryParamDTO,(page - 1)* limit,limit);
        Integer itemCount = postsTeamMapper.countAll(queryParamDTO);
        //返回结果
        return ReturnT.success.PAGE(itemList,itemCount);
    }*/
    @GetMapping("/list")
    public ModelAndView listPage(){
        return new ModelAndView("postsTeam-list");
    }

    @GetMapping("/edit")
    public ModelAndView editPage(int id){
        PostsTeam postsTeam = postsTeamMapper.selectOne(new QueryWrapper<PostsTeam>().eq("postsTeam_id",id));
        return new ModelAndView("postsTeam-edit","postsTeam",postsTeam);
    }

    /**
    * 发布/暂停(如不需要请屏蔽)
    */
    @PostMapping("/publish")
    public Object publish(int id,Integer status){
        PostsTeam postsTeam = postsTeamMapper.selectOne(new QueryWrapper<PostsTeam>().eq("postsTeam_id",id));
        if(postsTeam!=null){
            postsTeam.setUpdateTime(new Date());
            postsTeam.setStatus(status);
            postsTeamMapper.updateById(postsTeam);
            return ReturnT.success((status==1)?"已发布":"已暂停");
        }else if(status.equals(postsTeam.getStatus())){
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



