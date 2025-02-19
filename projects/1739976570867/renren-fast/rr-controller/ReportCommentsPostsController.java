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

import com.hzy.entity.ReportCommentsPostsEntity;
import com.hzy.service.ReportCommentsPostsService;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.R;


/**
* @description report_comments_posts控制器
* @author capkin
* @date 2025-02-19
*/
@RestController
@RequestMapping("generator/reportCommentsPosts")
public class ReportCommentsPostsController {

@Autowired
private ReportCommentsPostsService reportCommentsPostsService;

/**
* 列表
*/
@RequestMapping("/list")
@RequiresPermissions("generator:reportCommentsPosts:list")
public R list(@RequestParam Map<String, Object> params){
    PageUtils page = reportCommentsPostsService.queryPage(params);

    return R.ok().put("page", page);
}


/**
* 信息
*/
@RequestMapping("/info/{reportCommentsPostsId}")
@RequiresPermissions("generator:reportCommentsPosts:info")
public R info(@PathVariable("reportCommentsPostsId") int reportCommentsPostsId){
    ReportCommentsPostsEntity reportCommentsPosts = reportCommentsPostsService.getById(reportCommentsPostsId);

    return R.ok().put("reportCommentsPosts", reportCommentsPosts);
}

/**
* 保存
*/
@RequestMapping("/save")
@RequiresPermissions("generator:reportCommentsPosts:save")
public R save(@RequestBody ReportCommentsPostsEntity reportCommentsPosts){
    reportCommentsPostsService.save(reportCommentsPosts);

    return R.ok();
}

/**
* 修改
*/
@RequestMapping("/update")
@RequiresPermissions("generator:reportCommentsPosts:update")
public R update(@RequestBody ReportCommentsPostsEntity reportCommentsPosts){
    reportCommentsPostsService.updateById(reportCommentsPosts);

    return R.ok();
}

/**
* 删除
*/
@RequestMapping("/delete")
@RequiresPermissions("generator:reportCommentsPosts:delete")
public R delete(@RequestBody int[] reportCommentsPostsIds){
    reportCommentsPostsService.removeByIds(Arrays.asList(reportCommentsPostsIds));

    return R.ok();
}

}
