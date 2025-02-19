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

import com.hzy.entity.LeagueEntity;
import com.hzy.service.LeagueService;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.R;


/**
* @description league控制器
* @author capkin
* @date 2025-02-19
*/
@RestController
@RequestMapping("generator/league")
public class LeagueController {

@Autowired
private LeagueService leagueService;

/**
* 列表
*/
@RequestMapping("/list")
@RequiresPermissions("generator:league:list")
public R list(@RequestParam Map<String, Object> params){
    PageUtils page = leagueService.queryPage(params);

    return R.ok().put("page", page);
}


/**
* 信息
*/
@RequestMapping("/info/{leagueId}")
@RequiresPermissions("generator:league:info")
public R info(@PathVariable("leagueId") int leagueId){
    LeagueEntity league = leagueService.getById(leagueId);

    return R.ok().put("league", league);
}

/**
* 保存
*/
@RequestMapping("/save")
@RequiresPermissions("generator:league:save")
public R save(@RequestBody LeagueEntity league){
    leagueService.save(league);

    return R.ok();
}

/**
* 修改
*/
@RequestMapping("/update")
@RequiresPermissions("generator:league:update")
public R update(@RequestBody LeagueEntity league){
    leagueService.updateById(league);

    return R.ok();
}

/**
* 删除
*/
@RequestMapping("/delete")
@RequiresPermissions("generator:league:delete")
public R delete(@RequestBody int[] leagueIds){
    leagueService.removeByIds(Arrays.asList(leagueIds));

    return R.ok();
}

}
