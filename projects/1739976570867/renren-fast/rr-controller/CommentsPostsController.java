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

import com.hzy.entity.CommentsPostsEntity;
import com.hzy.service.CommentsPostsService;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.R;


/**
* @description comments_posts控制器
* @author capkin
* @date 2025-02-19
*/
@RestController
@RequestMapping("generator/commentsPosts")
public class CommentsPostsController {

@Autowired
private CommentsPostsService commentsPostsService;

/**
* 列表
*/
@RequestMapping("/list")
@RequiresPermissions("generator:commentsPosts:list")
public R list(@RequestParam Map<String, Object> params){
    PageUtils page = commentsPostsService.queryPage(params);

    return R.ok().put("page", page);
}


/**
* 信息
*/
@RequestMapping("/info/{commentsPostsId}")
@RequiresPermissions("generator:commentsPosts:info")
public R info(@PathVariable("commentsPostsId") int commentsPostsId){
    CommentsPostsEntity commentsPosts = commentsPostsService.getById(commentsPostsId);

    return R.ok().put("commentsPosts", commentsPosts);
}

/**
* 保存
*/
@RequestMapping("/save")
@RequiresPermissions("generator:commentsPosts:save")
public R save(@RequestBody CommentsPostsEntity commentsPosts){
    commentsPostsService.save(commentsPosts);

    return R.ok();
}

/**
* 修改
*/
@RequestMapping("/update")
@RequiresPermissions("generator:commentsPosts:update")
public R update(@RequestBody CommentsPostsEntity commentsPosts){
    commentsPostsService.updateById(commentsPosts);

    return R.ok();
}

/**
* 删除
*/
@RequestMapping("/delete")
@RequiresPermissions("generator:commentsPosts:delete")
public R delete(@RequestBody int[] commentsPostsIds){
    commentsPostsService.removeByIds(Arrays.asList(commentsPostsIds));

    return R.ok();
}

}
