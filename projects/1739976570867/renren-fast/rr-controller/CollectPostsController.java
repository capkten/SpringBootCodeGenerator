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

import com.hzy.entity.CollectPostsEntity;
import com.hzy.service.CollectPostsService;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.R;


/**
* @description collect_posts控制器
* @author capkin
* @date 2025-02-19
*/
@RestController
@RequestMapping("generator/collectPosts")
public class CollectPostsController {

@Autowired
private CollectPostsService collectPostsService;

/**
* 列表
*/
@RequestMapping("/list")
@RequiresPermissions("generator:collectPosts:list")
public R list(@RequestParam Map<String, Object> params){
    PageUtils page = collectPostsService.queryPage(params);

    return R.ok().put("page", page);
}


/**
* 信息
*/
@RequestMapping("/info/{collectPostsId}")
@RequiresPermissions("generator:collectPosts:info")
public R info(@PathVariable("collectPostsId") int collectPostsId){
    CollectPostsEntity collectPosts = collectPostsService.getById(collectPostsId);

    return R.ok().put("collectPosts", collectPosts);
}

/**
* 保存
*/
@RequestMapping("/save")
@RequiresPermissions("generator:collectPosts:save")
public R save(@RequestBody CollectPostsEntity collectPosts){
    collectPostsService.save(collectPosts);

    return R.ok();
}

/**
* 修改
*/
@RequestMapping("/update")
@RequiresPermissions("generator:collectPosts:update")
public R update(@RequestBody CollectPostsEntity collectPosts){
    collectPostsService.updateById(collectPosts);

    return R.ok();
}

/**
* 删除
*/
@RequestMapping("/delete")
@RequiresPermissions("generator:collectPosts:delete")
public R delete(@RequestBody int[] collectPostsIds){
    collectPostsService.removeByIds(Arrays.asList(collectPostsIds));

    return R.ok();
}

}
