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

import com.hzy.entity.PostsEntity;
import com.hzy.service.PostsService;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.R;


/**
* @description posts控制器
* @author capkin
* @date 2025-02-19
*/
@RestController
@RequestMapping("generator/posts")
public class PostsController {

@Autowired
private PostsService postsService;

/**
* 列表
*/
@RequestMapping("/list")
@RequiresPermissions("generator:posts:list")
public R list(@RequestParam Map<String, Object> params){
    PageUtils page = postsService.queryPage(params);

    return R.ok().put("page", page);
}


/**
* 信息
*/
@RequestMapping("/info/{postsId}")
@RequiresPermissions("generator:posts:info")
public R info(@PathVariable("postsId") int postsId){
    PostsEntity posts = postsService.getById(postsId);

    return R.ok().put("posts", posts);
}

/**
* 保存
*/
@RequestMapping("/save")
@RequiresPermissions("generator:posts:save")
public R save(@RequestBody PostsEntity posts){
    postsService.save(posts);

    return R.ok();
}

/**
* 修改
*/
@RequestMapping("/update")
@RequiresPermissions("generator:posts:update")
public R update(@RequestBody PostsEntity posts){
    postsService.updateById(posts);

    return R.ok();
}

/**
* 删除
*/
@RequestMapping("/delete")
@RequiresPermissions("generator:posts:delete")
public R delete(@RequestBody int[] postsIds){
    postsService.removeByIds(Arrays.asList(postsIds));

    return R.ok();
}

}
