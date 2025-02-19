package com.hzy.controller;
import com.hzy.entity.CommentsPosts;
import com.hzy.repository.CommentsPostsRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @description comments_posts
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/commentsPosts")
public class CommentsPostsController {

    @Autowired
    private CommentsPostsRepository commentsPostsRepository;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(CommentsPosts commentsPosts){
        return commentsPostsRepository.save(commentsPosts);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        Optional<CommentsPosts> commentsPosts=commentsPostsRepository.findById(id);
        if(commentsPosts.isPresent()){
            commentsPostsRepository.deleteById(id);
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
        Optional<CommentsPosts> commentsPosts=commentsPostsRepository.findById(id);
        if(commentsPosts.isPresent()){
            return ReturnT.success(commentsPosts.get());
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(CommentsPosts commentsPosts,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {

            //创建匹配器，需要查询条件请修改此处代码
            ExampleMatcher matcher = ExampleMatcher.matchingAll();

            //创建实例
            Example<CommentsPosts> example = Example.of(commentsPosts, matcher);
            //分页构造
            Pageable pageable = PageRequest.of(pageNumber,pageSize);

            return commentsPostsRepository.findAll(example, pageable);
    }

}
