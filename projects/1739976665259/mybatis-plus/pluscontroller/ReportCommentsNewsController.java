package com.hzy.controller;
import com.alibaba.fastjson.JSON;
import com.hzy.entity.ReportCommentsNews;
import com.hzy.mapper.ReportCommentsNewsMapper;
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
* @description report_comments_news控制器
* @author capkin
* @date 2025-02-19
*/
@Slf4j
@RestController
@RequestMapping("/reportCommentsNews")
public class ReportCommentsNewsController {

    @Autowired
    private ReportCommentsNewsMapper reportCommentsNewsMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(@RequestBody ReportCommentsNews reportCommentsNews){
        log.info("reportCommentsNews:"+JSON.toJSONString(reportCommentsNews));
        ReportCommentsNews oldReportCommentsNews = reportCommentsNewsMapper.selectOne(new QueryWrapper<ReportCommentsNews>().eq("reportCommentsNews_id",reportCommentsNews.getReportCommentsNewsId()));
        reportCommentsNews.setUpdateTime(new Date());
        if(oldReportCommentsNews!=null){
            reportCommentsNewsMapper.updateById(reportCommentsNews);
        }else{
        if(reportCommentsNewsMapper.selectOne(new QueryWrapper<ReportCommentsNews>().eq("reportCommentsNews_name",reportCommentsNews.getReportCommentsNewsName()))!=null){
            return ReturnT.error("保存失败，名字重复");
        }
        reportCommentsNews.setCreateTime(new Date());
        reportCommentsNewsMapper.insert(reportCommentsNews);
        }
        return ReturnT.success("保存成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
    ReportCommentsNews reportCommentsNews = reportCommentsNewsMapper.selectOne(new QueryWrapper<ReportCommentsNews>().eq("reportCommentsNews_id",id));
        if(reportCommentsNews!=null){
            reportCommentsNewsMapper.deleteById(id);
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
    ReportCommentsNews reportCommentsNews = reportCommentsNewsMapper.selectOne(new QueryWrapper<ReportCommentsNews>().eq("reportCommentsNews_id",id));
        if(reportCommentsNews!=null){
            return ReturnT.success(reportCommentsNews);
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
        Page<ReportCommentsNews> buildPage = new Page<ReportCommentsNews>(page,limit);
        //条件构造器
        QueryWrapper<ReportCommentsNews> queryWrapper = new QueryWrapper<ReportCommentsNews>();
        if(StringUtils.isNotEmpty(searchParams)&&JSON.isValid(searchParams)) {
            ReportCommentsNews reportCommentsNews = JSON.parseObject(searchParams, ReportCommentsNews.class);
            queryWrapper.eq(StringUtils.isNoneEmpty(reportCommentsNews.getReportCommentsNewsName()), "reportCommentsNews_name", reportCommentsNews.getReportCommentsNewsName());
        }
        //执行分页
        IPage<ReportCommentsNews> pageList = reportCommentsNewsMapper.selectPage(buildPage, queryWrapper);
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
        ReportCommentsNews queryParamDTO = JSON.parseObject(searchParams, ReportCommentsNews.class);
        //专用DTO模式
        //QueryParamDTO queryParamDTO = JSON.parseObject(searchParams, QueryParamDTO.class);
        //queryParamDTO.setPage((page - 1)* limit);
        //queryParamDTO.setLimit(limit);
        //(page - 1) * limit, limit
        List<ReportCommentsNews> itemList = reportCommentsNewsMapper.pageAll(queryParamDTO,(page - 1)* limit,limit);
        Integer itemCount = reportCommentsNewsMapper.countAll(queryParamDTO);
        //返回结果
        return ReturnT.success.PAGE(itemList,itemCount);
    }*/
    @GetMapping("/list")
    public ModelAndView listPage(){
        return new ModelAndView("reportCommentsNews-list");
    }

    @GetMapping("/edit")
    public ModelAndView editPage(int id){
        ReportCommentsNews reportCommentsNews = reportCommentsNewsMapper.selectOne(new QueryWrapper<ReportCommentsNews>().eq("reportCommentsNews_id",id));
        return new ModelAndView("reportCommentsNews-edit","reportCommentsNews",reportCommentsNews);
    }

    /**
    * 发布/暂停(如不需要请屏蔽)
    */
    @PostMapping("/publish")
    public Object publish(int id,Integer status){
        ReportCommentsNews reportCommentsNews = reportCommentsNewsMapper.selectOne(new QueryWrapper<ReportCommentsNews>().eq("reportCommentsNews_id",id));
        if(reportCommentsNews!=null){
            reportCommentsNews.setUpdateTime(new Date());
            reportCommentsNews.setStatus(status);
            reportCommentsNewsMapper.updateById(reportCommentsNews);
            return ReturnT.success((status==1)?"已发布":"已暂停");
        }else if(status.equals(reportCommentsNews.getStatus())){
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



