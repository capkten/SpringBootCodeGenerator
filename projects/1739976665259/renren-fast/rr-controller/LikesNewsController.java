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

import com.hzy.entity.LikesNewsEntity;
import com.hzy.service.LikesNewsService;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.R;


/**
* @description likes_news控制器
* @author capkin
* @date 2025-02-19
*/
@RestController
@RequestMapping("generator/likesNews")
public class LikesNewsController {

@Autowired
private LikesNewsService likesNewsService;

/**
* 列表
*/
@RequestMapping("/list")
@RequiresPermissions("generator:likesNews:list")
public R list(@RequestParam Map<String, Object> params){
    PageUtils page = likesNewsService.queryPage(params);

    return R.ok().put("page", page);
}


/**
* 信息
*/
@RequestMapping("/info/{likesNewsId}")
@RequiresPermissions("generator:likesNews:info")
public R info(@PathVariable("likesNewsId") int likesNewsId){
    LikesNewsEntity likesNews = likesNewsService.getById(likesNewsId);

    return R.ok().put("likesNews", likesNews);
}

/**
* 保存
*/
@RequestMapping("/save")
@RequiresPermissions("generator:likesNews:save")
public R save(@RequestBody LikesNewsEntity likesNews){
    likesNewsService.save(likesNews);

    return R.ok();
}

/**
* 修改
*/
@RequestMapping("/update")
@RequiresPermissions("generator:likesNews:update")
public R update(@RequestBody LikesNewsEntity likesNews){
    likesNewsService.updateById(likesNews);

    return R.ok();
}

/**
* 删除
*/
@RequestMapping("/delete")
@RequiresPermissions("generator:likesNews:delete")
public R delete(@RequestBody int[] likesNewsIds){
    likesNewsService.removeByIds(Arrays.asList(likesNewsIds));

    return R.ok();
}

}
