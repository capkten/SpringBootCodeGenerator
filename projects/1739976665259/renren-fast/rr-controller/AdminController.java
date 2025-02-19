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

import com.hzy.entity.AdminEntity;
import com.hzy.service.AdminService;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.R;


/**
* @description admin控制器
* @author capkin
* @date 2025-02-19
*/
@RestController
@RequestMapping("generator/admin")
public class AdminController {

@Autowired
private AdminService adminService;

/**
* 列表
*/
@RequestMapping("/list")
@RequiresPermissions("generator:admin:list")
public R list(@RequestParam Map<String, Object> params){
    PageUtils page = adminService.queryPage(params);

    return R.ok().put("page", page);
}


/**
* 信息
*/
@RequestMapping("/info/{adminId}")
@RequiresPermissions("generator:admin:info")
public R info(@PathVariable("adminId") int adminId){
    AdminEntity admin = adminService.getById(adminId);

    return R.ok().put("admin", admin);
}

/**
* 保存
*/
@RequestMapping("/save")
@RequiresPermissions("generator:admin:save")
public R save(@RequestBody AdminEntity admin){
    adminService.save(admin);

    return R.ok();
}

/**
* 修改
*/
@RequestMapping("/update")
@RequiresPermissions("generator:admin:update")
public R update(@RequestBody AdminEntity admin){
    adminService.updateById(admin);

    return R.ok();
}

/**
* 删除
*/
@RequestMapping("/delete")
@RequiresPermissions("generator:admin:delete")
public R delete(@RequestBody int[] adminIds){
    adminService.removeByIds(Arrays.asList(adminIds));

    return R.ok();
}

}
