package com.hzy.controller;
import com.alibaba.fastjson.JSON;
import com.hzy.entity.LikesPosts;
import com.hzy.mapper.LikesPostsMapper;
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
* @description likes_posts控制器
* @author capkin
* @date 2025-02-19
*/
@Slf4j
@RestController
@RequestMapping("/likesPosts")
public class LikesPostsController {

    @Autowired
    private LikesPostsMapper likesPostsMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(@RequestBody LikesPosts likesPosts){
        log.info("likesPosts:"+JSON.toJSONString(likesPosts));
        LikesPosts oldLikesPosts = likesPostsMapper.selectOne(new QueryWrapper<LikesPosts>().eq("likesPosts_id",likesPosts.getLikesPostsId()));
        likesPosts.setUpdateTime(new Date());
        if(oldLikesPosts!=null){
            likesPostsMapper.updateById(likesPosts);
        }else{
        if(likesPostsMapper.selectOne(new QueryWrapper<LikesPosts>().eq("likesPosts_name",likesPosts.getLikesPostsName()))!=null){
            return ReturnT.error("保存失败，名字重复");
        }
        likesPosts.setCreateTime(new Date());
        likesPostsMapper.insert(likesPosts);
        }
        return ReturnT.success("保存成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
    LikesPosts likesPosts = likesPostsMapper.selectOne(new QueryWrapper<LikesPosts>().eq("likesPosts_id",id));
        if(likesPosts!=null){
            likesPostsMapper.deleteById(id);
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
    LikesPosts likesPosts = likesPostsMapper.selectOne(new QueryWrapper<LikesPosts>().eq("likesPosts_id",id));
        if(likesPosts!=null){
            return ReturnT.success(likesPosts);
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
        Page<LikesPosts> buildPage = new Page<LikesPosts>(page,limit);
        //条件构造器
        QueryWrapper<LikesPosts> queryWrapper = new QueryWrapper<LikesPosts>();
        if(StringUtils.isNotEmpty(searchParams)&&JSON.isValid(searchParams)) {
            LikesPosts likesPosts = JSON.parseObject(searchParams, LikesPosts.class);
            queryWrapper.eq(StringUtils.isNoneEmpty(likesPosts.getLikesPostsName()), "likesPosts_name", likesPosts.getLikesPostsName());
        }
        //执行分页
        IPage<LikesPosts> pageList = likesPostsMapper.selectPage(buildPage, queryWrapper);
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
        LikesPosts queryParamDTO = JSON.parseObject(searchParams, LikesPosts.class);
        //专用DTO模式
        //QueryParamDTO queryParamDTO = JSON.parseObject(searchParams, QueryParamDTO.class);
        //queryParamDTO.setPage((page - 1)* limit);
        //queryParamDTO.setLimit(limit);
        //(page - 1) * limit, limit
        List<LikesPosts> itemList = likesPostsMapper.pageAll(queryParamDTO,(page - 1)* limit,limit);
        Integer itemCount = likesPostsMapper.countAll(queryParamDTO);
        //返回结果
        return ReturnT.success.PAGE(itemList,itemCount);
    }*/
    @GetMapping("/list")
    public ModelAndView listPage(){
        return new ModelAndView("likesPosts-list");
    }

    @GetMapping("/edit")
    public ModelAndView editPage(int id){
        LikesPosts likesPosts = likesPostsMapper.selectOne(new QueryWrapper<LikesPosts>().eq("likesPosts_id",id));
        return new ModelAndView("likesPosts-edit","likesPosts",likesPosts);
    }

    /**
    * 发布/暂停(如不需要请屏蔽)
    */
    @PostMapping("/publish")
    public Object publish(int id,Integer status){
        LikesPosts likesPosts = likesPostsMapper.selectOne(new QueryWrapper<LikesPosts>().eq("likesPosts_id",id));
        if(likesPosts!=null){
            likesPosts.setUpdateTime(new Date());
            likesPosts.setStatus(status);
            likesPostsMapper.updateById(likesPosts);
            return ReturnT.success((status==1)?"已发布":"已暂停");
        }else if(status.equals(likesPosts.getStatus())){
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



