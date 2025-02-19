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

import com.hzy.entity.LikesPostsEntity;
import com.hzy.service.LikesPostsService;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.R;


/**
* @description likes_posts控制器
* @author capkin
* @date 2025-02-19
*/
@RestController
@RequestMapping("generator/likesPosts")
public class LikesPostsController {

@Autowired
private LikesPostsService likesPostsService;

/**
* 列表
*/
@RequestMapping("/list")
@RequiresPermissions("generator:likesPosts:list")
public R list(@RequestParam Map<String, Object> params){
    PageUtils page = likesPostsService.queryPage(params);

    return R.ok().put("page", page);
}


/**
* 信息
*/
@RequestMapping("/info/{likesPostsId}")
@RequiresPermissions("generator:likesPosts:info")
public R info(@PathVariable("likesPostsId") int likesPostsId){
    LikesPostsEntity likesPosts = likesPostsService.getById(likesPostsId);

    return R.ok().put("likesPosts", likesPosts);
}

/**
* 保存
*/
@RequestMapping("/save")
@RequiresPermissions("generator:likesPosts:save")
public R save(@RequestBody LikesPostsEntity likesPosts){
    likesPostsService.save(likesPosts);

    return R.ok();
}

/**
* 修改
*/
@RequestMapping("/update")
@RequiresPermissions("generator:likesPosts:update")
public R update(@RequestBody LikesPostsEntity likesPosts){
    likesPostsService.updateById(likesPosts);

    return R.ok();
}

/**
* 删除
*/
@RequestMapping("/delete")
@RequiresPermissions("generator:likesPosts:delete")
public R delete(@RequestBody int[] likesPostsIds){
    likesPostsService.removeByIds(Arrays.asList(likesPostsIds));

    return R.ok();
}

}
