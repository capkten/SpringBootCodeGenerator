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

import com.hzy.entity.UsersEntity;
import com.hzy.service.UsersService;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.R;


/**
* @description users控制器
* @author capkin
* @date 2025-02-19
*/
@RestController
@RequestMapping("generator/users")
public class UsersController {

@Autowired
private UsersService usersService;

/**
* 列表
*/
@RequestMapping("/list")
@RequiresPermissions("generator:users:list")
public R list(@RequestParam Map<String, Object> params){
    PageUtils page = usersService.queryPage(params);

    return R.ok().put("page", page);
}


/**
* 信息
*/
@RequestMapping("/info/{usersId}")
@RequiresPermissions("generator:users:info")
public R info(@PathVariable("usersId") int usersId){
    UsersEntity users = usersService.getById(usersId);

    return R.ok().put("users", users);
}

/**
* 保存
*/
@RequestMapping("/save")
@RequiresPermissions("generator:users:save")
public R save(@RequestBody UsersEntity users){
    usersService.save(users);

    return R.ok();
}

/**
* 修改
*/
@RequestMapping("/update")
@RequiresPermissions("generator:users:update")
public R update(@RequestBody UsersEntity users){
    usersService.updateById(users);

    return R.ok();
}

/**
* 删除
*/
@RequestMapping("/delete")
@RequiresPermissions("generator:users:delete")
public R delete(@RequestBody int[] usersIds){
    usersService.removeByIds(Arrays.asList(usersIds));

    return R.ok();
}

}
