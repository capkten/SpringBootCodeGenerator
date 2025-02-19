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

import com.hzy.entity.NewsEditorEntity;
import com.hzy.service.NewsEditorService;
import com.hzy.common.utils.PageUtils;
import com.hzy.common.utils.R;


/**
* @description news_editor控制器
* @author capkin
* @date 2025-02-19
*/
@RestController
@RequestMapping("generator/newsEditor")
public class NewsEditorController {

@Autowired
private NewsEditorService newsEditorService;

/**
* 列表
*/
@RequestMapping("/list")
@RequiresPermissions("generator:newsEditor:list")
public R list(@RequestParam Map<String, Object> params){
    PageUtils page = newsEditorService.queryPage(params);

    return R.ok().put("page", page);
}


/**
* 信息
*/
@RequestMapping("/info/{newsEditorId}")
@RequiresPermissions("generator:newsEditor:info")
public R info(@PathVariable("newsEditorId") int newsEditorId){
    NewsEditorEntity newsEditor = newsEditorService.getById(newsEditorId);

    return R.ok().put("newsEditor", newsEditor);
}

/**
* 保存
*/
@RequestMapping("/save")
@RequiresPermissions("generator:newsEditor:save")
public R save(@RequestBody NewsEditorEntity newsEditor){
    newsEditorService.save(newsEditor);

    return R.ok();
}

/**
* 修改
*/
@RequestMapping("/update")
@RequiresPermissions("generator:newsEditor:update")
public R update(@RequestBody NewsEditorEntity newsEditor){
    newsEditorService.updateById(newsEditor);

    return R.ok();
}

/**
* 删除
*/
@RequestMapping("/delete")
@RequiresPermissions("generator:newsEditor:delete")
public R delete(@RequestBody int[] newsEditorIds){
    newsEditorService.removeByIds(Arrays.asList(newsEditorIds));

    return R.ok();
}

}
