package com.hzy.controller;
import com.hzy.entity.Posts;
import com.hzy.repository.PostsRepository;
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
 * @description posts
 * @author capkin
 * @date 2025-02-19
 */

@Slf4j
@Api(tags = "posts")
@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostsRepository postsRepository;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    @ApiOperation(value = "save Posts", notes = "save Posts")
    public Object save(@RequestBody Posts posts){
        try {
            return ReturnT.success(postsRepository.save(posts));
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnT.error("保存失败");
        }

    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    @ApiOperation(value = "delete Posts", notes = "delete Posts")
    public Object delete(int id){
        Optional<Posts> posts=postsRepository.findById(id);
        if(posts.isPresent()){
            postsRepository.deleteById(id);
            return ReturnT.success("删除成功");
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 查询
    */
    @PostMapping("/find")
    @ApiOperation(value = "find Posts by id", notes = "find Posts by id")
    public Object find(int id){
        Optional<Posts> posts=postsRepository.findById(id);
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
    @ApiOperation(value = "list Posts", notes = "list Posts")
    public Object list(@RequestBody Posts posts,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {

            try {
                //创建匹配器，需要查询条件请修改此处代码
                ExampleMatcher matcher = ExampleMatcher.matchingAll();

                //创建实例
                Example<Posts> example = Example.of(posts, matcher);
                //分页构造
                Pageable pageable = PageRequest.of(pageNumber,pageSize);

                return ReturnT.success(postsRepository.findAll(example, pageable));

            } catch (Exception e) {
                e.printStackTrace();
                return ReturnT.error(e.getMessage());
            }

    }

}
