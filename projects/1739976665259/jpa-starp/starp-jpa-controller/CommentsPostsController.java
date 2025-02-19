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

@Slf4j
@Api(tags = "commentsPosts")
@CrossOrigin
@RestController
@RequestMapping("/commentsPosts")
public class CommentsPostsController {

    @Autowired
    private CommentsPostsRepository commentsPostsRepository;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    @ApiOperation(value = "save CommentsPosts", notes = "save CommentsPosts")
    public Object save(@RequestBody CommentsPosts commentsPosts){
        try {
            return ReturnT.success(commentsPostsRepository.save(commentsPosts));
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnT.error("保存失败");
        }

    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    @ApiOperation(value = "delete CommentsPosts", notes = "delete CommentsPosts")
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
    @ApiOperation(value = "find CommentsPosts by id", notes = "find CommentsPosts by id")
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
    @ApiOperation(value = "list CommentsPosts", notes = "list CommentsPosts")
    public Object list(@RequestBody CommentsPosts commentsPosts,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {

            try {
                //创建匹配器，需要查询条件请修改此处代码
                ExampleMatcher matcher = ExampleMatcher.matchingAll();

                //创建实例
                Example<CommentsPosts> example = Example.of(commentsPosts, matcher);
                //分页构造
                Pageable pageable = PageRequest.of(pageNumber,pageSize);

                return ReturnT.success(commentsPostsRepository.findAll(example, pageable));

            } catch (Exception e) {
                e.printStackTrace();
                return ReturnT.error(e.getMessage());
            }

    }

}
