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

import com.hzy.entity.NewsLeagueEntity;
import com.hzy.service.NewsLeagueService;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.R;


/**
* @description news_league控制器
* @author capkin
* @date 2025-02-19
*/
@RestController
@RequestMapping("generator/newsLeague")
public class NewsLeagueController {

@Autowired
private NewsLeagueService newsLeagueService;

/**
* 列表
*/
@RequestMapping("/list")
@RequiresPermissions("generator:newsLeague:list")
public R list(@RequestParam Map<String, Object> params){
    PageUtils page = newsLeagueService.queryPage(params);

    return R.ok().put("page", page);
}


/**
* 信息
*/
@RequestMapping("/info/{newsLeagueId}")
@RequiresPermissions("generator:newsLeague:info")
public R info(@PathVariable("newsLeagueId") int newsLeagueId){
    NewsLeagueEntity newsLeague = newsLeagueService.getById(newsLeagueId);

    return R.ok().put("newsLeague", newsLeague);
}

/**
* 保存
*/
@RequestMapping("/save")
@RequiresPermissions("generator:newsLeague:save")
public R save(@RequestBody NewsLeagueEntity newsLeague){
    newsLeagueService.save(newsLeague);

    return R.ok();
}

/**
* 修改
*/
@RequestMapping("/update")
@RequiresPermissions("generator:newsLeague:update")
public R update(@RequestBody NewsLeagueEntity newsLeague){
    newsLeagueService.updateById(newsLeague);

    return R.ok();
}

/**
* 删除
*/
@RequestMapping("/delete")
@RequiresPermissions("generator:newsLeague:delete")
public R delete(@RequestBody int[] newsLeagueIds){
    newsLeagueService.removeByIds(Arrays.asList(newsLeagueIds));

    return R.ok();
}

}
