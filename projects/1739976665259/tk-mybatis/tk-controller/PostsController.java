package com.hzy.controller;
import com.hzy.entity.Posts;
import com.hzy.mapper.PostsMapper;
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
 * @description posts
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostsMapper postsMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(Posts posts){
        if(postsMapper.selectCount(posts)>0){
            postsMapper.insert(posts);
        }else{
            postsMapper.updateByPrimaryKeySelective(posts);
        }
        return ReturnT.success("新增或编辑成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        if(postsMapper.selectCount(posts)>0){
            postsMapper.deleteByPrimaryKey(id);
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
        Optional<Posts> posts=postsMapper.selectOne(id);
        if(posts.isPresent()){
            return ReturnT.success(posts.get());
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(Posts posts,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            //TBC
            return postsMapper.selectList(posts);
    }

}
