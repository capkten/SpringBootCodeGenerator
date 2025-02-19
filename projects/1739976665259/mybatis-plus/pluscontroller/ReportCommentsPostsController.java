package com.hzy.controller;
import com.alibaba.fastjson.JSON;
import com.hzy.entity.ReportCommentsPosts;
import com.hzy.mapper.ReportCommentsPostsMapper;
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
* @description report_comments_posts控制器
* @author capkin
* @date 2025-02-19
*/
@Slf4j
@RestController
@RequestMapping("/reportCommentsPosts")
public class ReportCommentsPostsController {

    @Autowired
    private ReportCommentsPostsMapper reportCommentsPostsMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(@RequestBody ReportCommentsPosts reportCommentsPosts){
        log.info("reportCommentsPosts:"+JSON.toJSONString(reportCommentsPosts));
        ReportCommentsPosts oldReportCommentsPosts = reportCommentsPostsMapper.selectOne(new QueryWrapper<ReportCommentsPosts>().eq("reportCommentsPosts_id",reportCommentsPosts.getReportCommentsPostsId()));
        reportCommentsPosts.setUpdateTime(new Date());
        if(oldReportCommentsPosts!=null){
            reportCommentsPostsMapper.updateById(reportCommentsPosts);
        }else{
        if(reportCommentsPostsMapper.selectOne(new QueryWrapper<ReportCommentsPosts>().eq("reportCommentsPosts_name",reportCommentsPosts.getReportCommentsPostsName()))!=null){
            return ReturnT.error("保存失败，名字重复");
        }
        reportCommentsPosts.setCreateTime(new Date());
        reportCommentsPostsMapper.insert(reportCommentsPosts);
        }
        return ReturnT.success("保存成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
    ReportCommentsPosts reportCommentsPosts = reportCommentsPostsMapper.selectOne(new QueryWrapper<ReportCommentsPosts>().eq("reportCommentsPosts_id",id));
        if(reportCommentsPosts!=null){
            reportCommentsPostsMapper.deleteById(id);
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
    ReportCommentsPosts reportCommentsPosts = reportCommentsPostsMapper.selectOne(new QueryWrapper<ReportCommentsPosts>().eq("reportCommentsPosts_id",id));
        if(reportCommentsPosts!=null){
            return ReturnT.success(reportCommentsPosts);
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
        Page<ReportCommentsPosts> buildPage = new Page<ReportCommentsPosts>(page,limit);
        //条件构造器
        QueryWrapper<ReportCommentsPosts> queryWrapper = new QueryWrapper<ReportCommentsPosts>();
        if(StringUtils.isNotEmpty(searchParams)&&JSON.isValid(searchParams)) {
            ReportCommentsPosts reportCommentsPosts = JSON.parseObject(searchParams, ReportCommentsPosts.class);
            queryWrapper.eq(StringUtils.isNoneEmpty(reportCommentsPosts.getReportCommentsPostsName()), "reportCommentsPosts_name", reportCommentsPosts.getReportCommentsPostsName());
        }
        //执行分页
        IPage<ReportCommentsPosts> pageList = reportCommentsPostsMapper.selectPage(buildPage, queryWrapper);
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
        ReportCommentsPosts queryParamDTO = JSON.parseObject(searchParams, ReportCommentsPosts.class);
        //专用DTO模式
        //QueryParamDTO queryParamDTO = JSON.parseObject(searchParams, QueryParamDTO.class);
        //queryParamDTO.setPage((page - 1)* limit);
        //queryParamDTO.setLimit(limit);
        //(page - 1) * limit, limit
        List<ReportCommentsPosts> itemList = reportCommentsPostsMapper.pageAll(queryParamDTO,(page - 1)* limit,limit);
        Integer itemCount = reportCommentsPostsMapper.countAll(queryParamDTO);
        //返回结果
        return ReturnT.success.PAGE(itemList,itemCount);
    }*/
    @GetMapping("/list")
    public ModelAndView listPage(){
        return new ModelAndView("reportCommentsPosts-list");
    }

    @GetMapping("/edit")
    public ModelAndView editPage(int id){
        ReportCommentsPosts reportCommentsPosts = reportCommentsPostsMapper.selectOne(new QueryWrapper<ReportCommentsPosts>().eq("reportCommentsPosts_id",id));
        return new ModelAndView("reportCommentsPosts-edit","reportCommentsPosts",reportCommentsPosts);
    }

    /**
    * 发布/暂停(如不需要请屏蔽)
    */
    @PostMapping("/publish")
    public Object publish(int id,Integer status){
        ReportCommentsPosts reportCommentsPosts = reportCommentsPostsMapper.selectOne(new QueryWrapper<ReportCommentsPosts>().eq("reportCommentsPosts_id",id));
        if(reportCommentsPosts!=null){
            reportCommentsPosts.setUpdateTime(new Date());
            reportCommentsPosts.setStatus(status);
            reportCommentsPostsMapper.updateById(reportCommentsPosts);
            return ReturnT.success((status==1)?"已发布":"已暂停");
        }else if(status.equals(reportCommentsPosts.getStatus())){
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



