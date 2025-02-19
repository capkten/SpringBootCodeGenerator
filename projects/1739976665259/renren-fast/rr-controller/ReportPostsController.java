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

import com.hzy.entity.ReportPostsEntity;
import com.hzy.service.ReportPostsService;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.R;


/**
* @description report_posts控制器
* @author capkin
* @date 2025-02-19
*/
@RestController
@RequestMapping("generator/reportPosts")
public class ReportPostsController {

@Autowired
private ReportPostsService reportPostsService;

/**
* 列表
*/
@RequestMapping("/list")
@RequiresPermissions("generator:reportPosts:list")
public R list(@RequestParam Map<String, Object> params){
    PageUtils page = reportPostsService.queryPage(params);

    return R.ok().put("page", page);
}


/**
* 信息
*/
@RequestMapping("/info/{reportPostsId}")
@RequiresPermissions("generator:reportPosts:info")
public R info(@PathVariable("reportPostsId") int reportPostsId){
    ReportPostsEntity reportPosts = reportPostsService.getById(reportPostsId);

    return R.ok().put("reportPosts", reportPosts);
}

/**
* 保存
*/
@RequestMapping("/save")
@RequiresPermissions("generator:reportPosts:save")
public R save(@RequestBody ReportPostsEntity reportPosts){
    reportPostsService.save(reportPosts);

    return R.ok();
}

/**
* 修改
*/
@RequestMapping("/update")
@RequiresPermissions("generator:reportPosts:update")
public R update(@RequestBody ReportPostsEntity reportPosts){
    reportPostsService.updateById(reportPosts);

    return R.ok();
}

/**
* 删除
*/
@RequestMapping("/delete")
@RequiresPermissions("generator:reportPosts:delete")
public R delete(@RequestBody int[] reportPostsIds){
    reportPostsService.removeByIds(Arrays.asList(reportPostsIds));

    return R.ok();
}

}
