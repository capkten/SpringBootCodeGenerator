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

import com.hzy.entity.PostsTeamEntity;
import com.hzy.service.PostsTeamService;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.R;


/**
* @description posts_team控制器
* @author capkin
* @date 2025-02-19
*/
@RestController
@RequestMapping("generator/postsTeam")
public class PostsTeamController {

@Autowired
private PostsTeamService postsTeamService;

/**
* 列表
*/
@RequestMapping("/list")
@RequiresPermissions("generator:postsTeam:list")
public R list(@RequestParam Map<String, Object> params){
    PageUtils page = postsTeamService.queryPage(params);

    return R.ok().put("page", page);
}


/**
* 信息
*/
@RequestMapping("/info/{postsTeamId}")
@RequiresPermissions("generator:postsTeam:info")
public R info(@PathVariable("postsTeamId") int postsTeamId){
    PostsTeamEntity postsTeam = postsTeamService.getById(postsTeamId);

    return R.ok().put("postsTeam", postsTeam);
}

/**
* 保存
*/
@RequestMapping("/save")
@RequiresPermissions("generator:postsTeam:save")
public R save(@RequestBody PostsTeamEntity postsTeam){
    postsTeamService.save(postsTeam);

    return R.ok();
}

/**
* 修改
*/
@RequestMapping("/update")
@RequiresPermissions("generator:postsTeam:update")
public R update(@RequestBody PostsTeamEntity postsTeam){
    postsTeamService.updateById(postsTeam);

    return R.ok();
}

/**
* 删除
*/
@RequestMapping("/delete")
@RequiresPermissions("generator:postsTeam:delete")
public R delete(@RequestBody int[] postsTeamIds){
    postsTeamService.removeByIds(Arrays.asList(postsTeamIds));

    return R.ok();
}

}
