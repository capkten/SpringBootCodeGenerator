package com.hzy.controller;
import com.hzy.entity.CollectPosts;
import com.hzy.mapper.CollectPostsMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @description collect_posts
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/collectPosts")
public class CollectPostsController {

    @Autowired
    private CollectPostsMapper collectPostsMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(CollectPosts collectPosts){
        if(collectPostsMapper.selectCount(collectPosts)>0){
            collectPostsMapper.insert(collectPosts);
        }else{
            collectPostsMapper.updateByPrimaryKeySelective(collectPosts);
        }
        return ReturnT.success("新增或编辑成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        if(collectPostsMapper.selectCount(collectPosts)>0){
            collectPostsMapper.deleteByPrimaryKey(id);
            return ReturnT.success("删除成功");
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 查询
    */
    @PostMapping("/find")
    public Object find(int id){
        Optional<CollectPosts> collectPosts=collectPostsMapper.selectOne(id);
        if(collectPosts.isPresent()){
            return ReturnT.success(collectPosts.get());
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(CollectPosts collectPosts,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            //TBC
            return collectPostsMapper.selectList(collectPosts);
    }

}
