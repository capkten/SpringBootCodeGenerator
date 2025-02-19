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

import com.hzy.entity.NewsEntity;
import com.hzy.service.NewsService;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.R;


/**
* @description news控制器
* @author capkin
* @date 2025-02-19
*/
@RestController
@RequestMapping("generator/news")
public class NewsController {

@Autowired
private NewsService newsService;

/**
* 列表
*/
@RequestMapping("/list")
@RequiresPermissions("generator:news:list")
public R list(@RequestParam Map<String, Object> params){
    PageUtils page = newsService.queryPage(params);

    return R.ok().put("page", page);
}


/**
* 信息
*/
@RequestMapping("/info/{newsId}")
@RequiresPermissions("generator:news:info")
public R info(@PathVariable("newsId") int newsId){
    NewsEntity news = newsService.getById(newsId);

    return R.ok().put("news", news);
}

/**
* 保存
*/
@RequestMapping("/save")
@RequiresPermissions("generator:news:save")
public R save(@RequestBody NewsEntity news){
    newsService.save(news);

    return R.ok();
}

/**
* 修改
*/
@RequestMapping("/update")
@RequiresPermissions("generator:news:update")
public R update(@RequestBody NewsEntity news){
    newsService.updateById(news);

    return R.ok();
}

/**
* 删除
*/
@RequestMapping("/delete")
@RequiresPermissions("generator:news:delete")
public R delete(@RequestBody int[] newsIds){
    newsService.removeByIds(Arrays.asList(newsIds));

    return R.ok();
}

}
