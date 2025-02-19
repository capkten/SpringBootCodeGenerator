package com.hzy.controller;
import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hzy.entity.ReportCommentsNewsEntity;
import com.hzy.service.ReportCommentsNewsService;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.R;


/**
* @description report_comments_news控制器
* @author capkin
* @date 2025-02-19
*/
@RestController
@RequestMapping("generator/reportCommentsNews")
public class ReportCommentsNewsController {

@Autowired
private ReportCommentsNewsService reportCommentsNewsService;

/**
* 列表
*/
@RequestMapping("/list")
@RequiresPermissions("generator:reportCommentsNews:list")
public R list(@RequestParam Map<String, Object> params){
    PageUtils page = reportCommentsNewsService.queryPage(params);

    return R.ok().put("page", page);
}


/**
* 信息
*/
@RequestMapping("/info/{reportCommentsNewsId}")
@RequiresPermissions("generator:reportCommentsNews:info")
public R info(@PathVariable("reportCommentsNewsId") int reportCommentsNewsId){
    ReportCommentsNewsEntity reportCommentsNews = reportCommentsNewsService.getById(reportCommentsNewsId);

    return R.ok().put("reportCommentsNews", reportCommentsNews);
}

/**
* 保存
*/
@RequestMapping("/save")
@RequiresPermissions("generator:reportCommentsNews:save")
public R save(@RequestBody ReportCommentsNewsEntity reportCommentsNews){
    reportCommentsNewsService.save(reportCommentsNews);

    return R.ok();
}

/**
* 修改
*/
@RequestMapping("/update")
@RequiresPermissions("generator:reportCommentsNews:update")
public R update(@RequestBody ReportCommentsNewsEntity reportCommentsNews){
    reportCommentsNewsService.updateById(reportCommentsNews);

    return R.ok();
}

/**
* 删除
*/
@RequestMapping("/delete")
@RequiresPermissions("generator:reportCommentsNews:delete")
public R delete(@RequestBody int[] reportCommentsNewsIds){
    reportCommentsNewsService.removeByIds(Arrays.asList(reportCommentsNewsIds));

    return R.ok();
}

}
