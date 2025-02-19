package com.hzy.controller;
import com.alibaba.fastjson.JSON;
import com.hzy.entity.ReportPosts;
import com.hzy.mapper.ReportPostsMapper;
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
* @description report_posts控制器
* @author capkin
* @date 2025-02-19
*/
@Slf4j
@RestController
@RequestMapping("/reportPosts")
public class ReportPostsController {

    @Autowired
    private ReportPostsMapper reportPostsMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(@RequestBody ReportPosts reportPosts){
        log.info("reportPosts:"+JSON.toJSONString(reportPosts));
        ReportPosts oldReportPosts = reportPostsMapper.selectOne(new QueryWrapper<ReportPosts>().eq("reportPosts_id",reportPosts.getReportPostsId()));
        reportPosts.setUpdateTime(new Date());
        if(oldReportPosts!=null){
            reportPostsMapper.updateById(reportPosts);
        }else{
        if(reportPostsMapper.selectOne(new QueryWrapper<ReportPosts>().eq("reportPosts_name",reportPosts.getReportPostsName()))!=null){
            return ReturnT.error("保存失败，名字重复");
        }
        reportPosts.setCreateTime(new Date());
        reportPostsMapper.insert(reportPosts);
        }
        return ReturnT.success("保存成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
    ReportPosts reportPosts = reportPostsMapper.selectOne(new QueryWrapper<ReportPosts>().eq("reportPosts_id",id));
        if(reportPosts!=null){
            reportPostsMapper.deleteById(id);
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
    ReportPosts reportPosts = reportPostsMapper.selectOne(new QueryWrapper<ReportPosts>().eq("reportPosts_id",id));
        if(reportPosts!=null){
            return ReturnT.success(reportPosts);
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
        Page<ReportPosts> buildPage = new Page<ReportPosts>(page,limit);
        //条件构造器
        QueryWrapper<ReportPosts> queryWrapper = new QueryWrapper<ReportPosts>();
        if(StringUtils.isNotEmpty(searchParams)&&JSON.isValid(searchParams)) {
            ReportPosts reportPosts = JSON.parseObject(searchParams, ReportPosts.class);
            queryWrapper.eq(StringUtils.isNoneEmpty(reportPosts.getReportPostsName()), "reportPosts_name", reportPosts.getReportPostsName());
        }
        //执行分页
        IPage<ReportPosts> pageList = reportPostsMapper.selectPage(buildPage, queryWrapper);
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
        ReportPosts queryParamDTO = JSON.parseObject(searchParams, ReportPosts.class);
        //专用DTO模式
        //QueryParamDTO queryParamDTO = JSON.parseObject(searchParams, QueryParamDTO.class);
        //queryParamDTO.setPage((page - 1)* limit);
        //queryParamDTO.setLimit(limit);
        //(page - 1) * limit, limit
        List<ReportPosts> itemList = reportPostsMapper.pageAll(queryParamDTO,(page - 1)* limit,limit);
        Integer itemCount = reportPostsMapper.countAll(queryParamDTO);
        //返回结果
        return ReturnT.success.PAGE(itemList,itemCount);
    }*/
    @GetMapping("/list")
    public ModelAndView listPage(){
        return new ModelAndView("reportPosts-list");
    }

    @GetMapping("/edit")
    public ModelAndView editPage(int id){
        ReportPosts reportPosts = reportPostsMapper.selectOne(new QueryWrapper<ReportPosts>().eq("reportPosts_id",id));
        return new ModelAndView("reportPosts-edit","reportPosts",reportPosts);
    }

    /**
    * 发布/暂停(如不需要请屏蔽)
    */
    @PostMapping("/publish")
    public Object publish(int id,Integer status){
        ReportPosts reportPosts = reportPostsMapper.selectOne(new QueryWrapper<ReportPosts>().eq("reportPosts_id",id));
        if(reportPosts!=null){
            reportPosts.setUpdateTime(new Date());
            reportPosts.setStatus(status);
            reportPostsMapper.updateById(reportPosts);
            return ReturnT.success((status==1)?"已发布":"已暂停");
        }else if(status.equals(reportPosts.getStatus())){
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



