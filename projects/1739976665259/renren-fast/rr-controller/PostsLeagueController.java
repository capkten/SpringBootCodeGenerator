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

import com.hzy.entity.PostsLeagueEntity;
import com.hzy.service.PostsLeagueService;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.R;


/**
* @description posts_league控制器
* @author capkin
* @date 2025-02-19
*/
@RestController
@RequestMapping("generator/postsLeague")
public class PostsLeagueController {

@Autowired
private PostsLeagueService postsLeagueService;

/**
* 列表
*/
@RequestMapping("/list")
@RequiresPermissions("generator:postsLeague:list")
public R list(@RequestParam Map<String, Object> params){
    PageUtils page = postsLeagueService.queryPage(params);

    return R.ok().put("page", page);
}


/**
* 信息
*/
@RequestMapping("/info/{postsLeagueId}")
@RequiresPermissions("generator:postsLeague:info")
public R info(@PathVariable("postsLeagueId") int postsLeagueId){
    PostsLeagueEntity postsLeague = postsLeagueService.getById(postsLeagueId);

    return R.ok().put("postsLeague", postsLeague);
}

/**
* 保存
*/
@RequestMapping("/save")
@RequiresPermissions("generator:postsLeague:save")
public R save(@RequestBody PostsLeagueEntity postsLeague){
    postsLeagueService.save(postsLeague);

    return R.ok();
}

/**
* 修改
*/
@RequestMapping("/update")
@RequiresPermissions("generator:postsLeague:update")
public R update(@RequestBody PostsLeagueEntity postsLeague){
    postsLeagueService.updateById(postsLeague);

    return R.ok();
}

/**
* 删除
*/
@RequestMapping("/delete")
@RequiresPermissions("generator:postsLeague:delete")
public R delete(@RequestBody int[] postsLeagueIds){
    postsLeagueService.removeByIds(Arrays.asList(postsLeagueIds));

    return R.ok();
}

}
