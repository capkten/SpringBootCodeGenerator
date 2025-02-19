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

import com.hzy.entity.TeamEntity;
import com.hzy.service.TeamService;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.R;


/**
* @description team控制器
* @author capkin
* @date 2025-02-19
*/
@RestController
@RequestMapping("generator/team")
public class TeamController {

@Autowired
private TeamService teamService;

/**
* 列表
*/
@RequestMapping("/list")
@RequiresPermissions("generator:team:list")
public R list(@RequestParam Map<String, Object> params){
    PageUtils page = teamService.queryPage(params);

    return R.ok().put("page", page);
}


/**
* 信息
*/
@RequestMapping("/info/{teamId}")
@RequiresPermissions("generator:team:info")
public R info(@PathVariable("teamId") int teamId){
    TeamEntity team = teamService.getById(teamId);

    return R.ok().put("team", team);
}

/**
* 保存
*/
@RequestMapping("/save")
@RequiresPermissions("generator:team:save")
public R save(@RequestBody TeamEntity team){
    teamService.save(team);

    return R.ok();
}

/**
* 修改
*/
@RequestMapping("/update")
@RequiresPermissions("generator:team:update")
public R update(@RequestBody TeamEntity team){
    teamService.updateById(team);

    return R.ok();
}

/**
* 删除
*/
@RequestMapping("/delete")
@RequiresPermissions("generator:team:delete")
public R delete(@RequestBody int[] teamIds){
    teamService.removeByIds(Arrays.asList(teamIds));

    return R.ok();
}

}
