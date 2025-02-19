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

import com.hzy.entity.NewsTeamEntity;
import com.hzy.service.NewsTeamService;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.R;


/**
* @description news_team控制器
* @author capkin
* @date 2025-02-19
*/
@RestController
@RequestMapping("generator/newsTeam")
public class NewsTeamController {

@Autowired
private NewsTeamService newsTeamService;

/**
* 列表
*/
@RequestMapping("/list")
@RequiresPermissions("generator:newsTeam:list")
public R list(@RequestParam Map<String, Object> params){
    PageUtils page = newsTeamService.queryPage(params);

    return R.ok().put("page", page);
}


/**
* 信息
*/
@RequestMapping("/info/{newsTeamId}")
@RequiresPermissions("generator:newsTeam:info")
public R info(@PathVariable("newsTeamId") int newsTeamId){
    NewsTeamEntity newsTeam = newsTeamService.getById(newsTeamId);

    return R.ok().put("newsTeam", newsTeam);
}

/**
* 保存
*/
@RequestMapping("/save")
@RequiresPermissions("generator:newsTeam:save")
public R save(@RequestBody NewsTeamEntity newsTeam){
    newsTeamService.save(newsTeam);

    return R.ok();
}

/**
* 修改
*/
@RequestMapping("/update")
@RequiresPermissions("generator:newsTeam:update")
public R update(@RequestBody NewsTeamEntity newsTeam){
    newsTeamService.updateById(newsTeam);

    return R.ok();
}

/**
* 删除
*/
@RequestMapping("/delete")
@RequiresPermissions("generator:newsTeam:delete")
public R delete(@RequestBody int[] newsTeamIds){
    newsTeamService.removeByIds(Arrays.asList(newsTeamIds));

    return R.ok();
}

}
