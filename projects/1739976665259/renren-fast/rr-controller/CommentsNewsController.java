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

import com.hzy.entity.CommentsNewsEntity;
import com.hzy.service.CommentsNewsService;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.R;


/**
* @description comments_news控制器
* @author capkin
* @date 2025-02-19
*/
@RestController
@RequestMapping("generator/commentsNews")
public class CommentsNewsController {

@Autowired
private CommentsNewsService commentsNewsService;

/**
* 列表
*/
@RequestMapping("/list")
@RequiresPermissions("generator:commentsNews:list")
public R list(@RequestParam Map<String, Object> params){
    PageUtils page = commentsNewsService.queryPage(params);

    return R.ok().put("page", page);
}


/**
* 信息
*/
@RequestMapping("/info/{commentsNewsId}")
@RequiresPermissions("generator:commentsNews:info")
public R info(@PathVariable("commentsNewsId") int commentsNewsId){
    CommentsNewsEntity commentsNews = commentsNewsService.getById(commentsNewsId);

    return R.ok().put("commentsNews", commentsNews);
}

/**
* 保存
*/
@RequestMapping("/save")
@RequiresPermissions("generator:commentsNews:save")
public R save(@RequestBody CommentsNewsEntity commentsNews){
    commentsNewsService.save(commentsNews);

    return R.ok();
}

/**
* 修改
*/
@RequestMapping("/update")
@RequiresPermissions("generator:commentsNews:update")
public R update(@RequestBody CommentsNewsEntity commentsNews){
    commentsNewsService.updateById(commentsNews);

    return R.ok();
}

/**
* 删除
*/
@RequestMapping("/delete")
@RequiresPermissions("generator:commentsNews:delete")
public R delete(@RequestBody int[] commentsNewsIds){
    commentsNewsService.removeByIds(Arrays.asList(commentsNewsIds));

    return R.ok();
}

}
