package com.hzy.controller;
import com.alibaba.fastjson.JSON;
import com.hzy.entity.CollectPosts;
import com.hzy.mapper.CollectPostsMapper;
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
* @description collect_posts控制器
* @author capkin
* @date 2025-02-19
*/
@Slf4j
@RestController
@RequestMapping("/collectPosts")
public class CollectPostsController {

    @Autowired
    private CollectPostsMapper collectPostsMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(@RequestBody CollectPosts collectPosts){
        log.info("collectPosts:"+JSON.toJSONString(collectPosts));
        CollectPosts oldCollectPosts = collectPostsMapper.selectOne(new QueryWrapper<CollectPosts>().eq("collectPosts_id",collectPosts.getCollectPostsId()));
        collectPosts.setUpdateTime(new Date());
        if(oldCollectPosts!=null){
            collectPostsMapper.updateById(collectPosts);
        }else{
        if(collectPostsMapper.selectOne(new QueryWrapper<CollectPosts>().eq("collectPosts_name",collectPosts.getCollectPostsName()))!=null){
            return ReturnT.error("保存失败，名字重复");
        }
        collectPosts.setCreateTime(new Date());
        collectPostsMapper.insert(collectPosts);
        }
        return ReturnT.success("保存成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
    CollectPosts collectPosts = collectPostsMapper.selectOne(new QueryWrapper<CollectPosts>().eq("collectPosts_id",id));
        if(collectPosts!=null){
            collectPostsMapper.deleteById(id);
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
    CollectPosts collectPosts = collectPostsMapper.selectOne(new QueryWrapper<CollectPosts>().eq("collectPosts_id",id));
        if(collectPosts!=null){
            return ReturnT.success(collectPosts);
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
        Page<CollectPosts> buildPage = new Page<CollectPosts>(page,limit);
        //条件构造器
        QueryWrapper<CollectPosts> queryWrapper = new QueryWrapper<CollectPosts>();
        if(StringUtils.isNotEmpty(searchParams)&&JSON.isValid(searchParams)) {
            CollectPosts collectPosts = JSON.parseObject(searchParams, CollectPosts.class);
            queryWrapper.eq(StringUtils.isNoneEmpty(collectPosts.getCollectPostsName()), "collectPosts_name", collectPosts.getCollectPostsName());
        }
        //执行分页
        IPage<CollectPosts> pageList = collectPostsMapper.selectPage(buildPage, queryWrapper);
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
        CollectPosts queryParamDTO = JSON.parseObject(searchParams, CollectPosts.class);
        //专用DTO模式
        //QueryParamDTO queryParamDTO = JSON.parseObject(searchParams, QueryParamDTO.class);
        //queryParamDTO.setPage((page - 1)* limit);
        //queryParamDTO.setLimit(limit);
        //(page - 1) * limit, limit
        List<CollectPosts> itemList = collectPostsMapper.pageAll(queryParamDTO,(page - 1)* limit,limit);
        Integer itemCount = collectPostsMapper.countAll(queryParamDTO);
        //返回结果
        return ReturnT.success.PAGE(itemList,itemCount);
    }*/
    @GetMapping("/list")
    public ModelAndView listPage(){
        return new ModelAndView("collectPosts-list");
    }

    @GetMapping("/edit")
    public ModelAndView editPage(int id){
        CollectPosts collectPosts = collectPostsMapper.selectOne(new QueryWrapper<CollectPosts>().eq("collectPosts_id",id));
        return new ModelAndView("collectPosts-edit","collectPosts",collectPosts);
    }

    /**
    * 发布/暂停(如不需要请屏蔽)
    */
    @PostMapping("/publish")
    public Object publish(int id,Integer status){
        CollectPosts collectPosts = collectPostsMapper.selectOne(new QueryWrapper<CollectPosts>().eq("collectPosts_id",id));
        if(collectPosts!=null){
            collectPosts.setUpdateTime(new Date());
            collectPosts.setStatus(status);
            collectPostsMapper.updateById(collectPosts);
            return ReturnT.success((status==1)?"已发布":"已暂停");
        }else if(status.equals(collectPosts.getStatus())){
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



