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

import com.hzy.entity.CollectNewsEntity;
import com.hzy.service.CollectNewsService;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.R;


/**
* @description collect_news控制器
* @author capkin
* @date 2025-02-19
*/
@RestController
@RequestMapping("generator/collectNews")
public class CollectNewsController {

@Autowired
private CollectNewsService collectNewsService;

/**
* 列表
*/
@RequestMapping("/list")
@RequiresPermissions("generator:collectNews:list")
public R list(@RequestParam Map<String, Object> params){
    PageUtils page = collectNewsService.queryPage(params);

    return R.ok().put("page", page);
}


/**
* 信息
*/
@RequestMapping("/info/{collectNewsId}")
@RequiresPermissions("generator:collectNews:info")
public R info(@PathVariable("collectNewsId") int collectNewsId){
    CollectNewsEntity collectNews = collectNewsService.getById(collectNewsId);

    return R.ok().put("collectNews", collectNews);
}

/**
* 保存
*/
@RequestMapping("/save")
@RequiresPermissions("generator:collectNews:save")
public R save(@RequestBody CollectNewsEntity collectNews){
    collectNewsService.save(collectNews);

    return R.ok();
}

/**
* 修改
*/
@RequestMapping("/update")
@RequiresPermissions("generator:collectNews:update")
public R update(@RequestBody CollectNewsEntity collectNews){
    collectNewsService.updateById(collectNews);

    return R.ok();
}

/**
* 删除
*/
@RequestMapping("/delete")
@RequiresPermissions("generator:collectNews:delete")
public R delete(@RequestBody int[] collectNewsIds){
    collectNewsService.removeByIds(Arrays.asList(collectNewsIds));

    return R.ok();
}

}
