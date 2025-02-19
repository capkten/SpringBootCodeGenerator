package com.hzy.controller;
import com.alibaba.fastjson.JSON;
import com.hzy.entity.Posts;
import com.hzy.mapper.PostsMapper;
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
* @description posts控制器
* @author capkin
* @date 2025-02-19
*/
@Slf4j
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostsMapper postsMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(@RequestBody Posts posts){
        log.info("posts:"+JSON.toJSONString(posts));
        Posts oldPosts = postsMapper.selectOne(new QueryWrapper<Posts>().eq("posts_id",posts.getPostsId()));
        posts.setUpdateTime(new Date());
        if(oldPosts!=null){
            postsMapper.updateById(posts);
        }else{
        if(postsMapper.selectOne(new QueryWrapper<Posts>().eq("posts_name",posts.getPostsName()))!=null){
            return ReturnT.error("保存失败，名字重复");
        }
        posts.setCreateTime(new Date());
        postsMapper.insert(posts);
        }
        return ReturnT.success("保存成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
    Posts posts = postsMapper.selectOne(new QueryWrapper<Posts>().eq("posts_id",id));
        if(posts!=null){
            postsMapper.deleteById(id);
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
    Posts posts = postsMapper.selectOne(new QueryWrapper<Posts>().eq("posts_id",id));
        if(posts!=null){
            return ReturnT.success(posts);
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
        Page<Posts> buildPage = new Page<Posts>(page,limit);
        //条件构造器
        QueryWrapper<Posts> queryWrapper = new QueryWrapper<Posts>();
        if(StringUtils.isNotEmpty(searchParams)&&JSON.isValid(searchParams)) {
            Posts posts = JSON.parseObject(searchParams, Posts.class);
            queryWrapper.eq(StringUtils.isNoneEmpty(posts.getPostsName()), "posts_name", posts.getPostsName());
        }
        //执行分页
        IPage<Posts> pageList = postsMapper.selectPage(buildPage, queryWrapper);
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
        Posts queryParamDTO = JSON.parseObject(searchParams, Posts.class);
        //专用DTO模式
        //QueryParamDTO queryParamDTO = JSON.parseObject(searchParams, QueryParamDTO.class);
        //queryParamDTO.setPage((page - 1)* limit);
        //queryParamDTO.setLimit(limit);
        //(page - 1) * limit, limit
        List<Posts> itemList = postsMapper.pageAll(queryParamDTO,(page - 1)* limit,limit);
        Integer itemCount = postsMapper.countAll(queryParamDTO);
        //返回结果
        return ReturnT.success.PAGE(itemList,itemCount);
    }*/
    @GetMapping("/list")
    public ModelAndView listPage(){
        return new ModelAndView("posts-list");
    }

    @GetMapping("/edit")
    public ModelAndView editPage(int id){
        Posts posts = postsMapper.selectOne(new QueryWrapper<Posts>().eq("posts_id",id));
        return new ModelAndView("posts-edit","posts",posts);
    }

    /**
    * 发布/暂停(如不需要请屏蔽)
    */
    @PostMapping("/publish")
    public Object publish(int id,Integer status){
        Posts posts = postsMapper.selectOne(new QueryWrapper<Posts>().eq("posts_id",id));
        if(posts!=null){
            posts.setUpdateTime(new Date());
            posts.setStatus(status);
            postsMapper.updateById(posts);
            return ReturnT.success((status==1)?"已发布":"已暂停");
        }else if(status.equals(posts.getStatus())){
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



