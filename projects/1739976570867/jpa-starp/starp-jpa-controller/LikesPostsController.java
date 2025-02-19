package com.hzy.controller;
import com.hzy.entity.LikesPosts;
import com.hzy.repository.LikesPostsRepository;
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
 * @description likes_posts
 * @author capkin
 * @date 2025-02-19
 */

@Slf4j
@Api(tags = "likesPosts")
@CrossOrigin
@RestController
@RequestMapping("/likesPosts")
public class LikesPostsController {

    @Autowired
    private LikesPostsRepository likesPostsRepository;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    @ApiOperation(value = "save LikesPosts", notes = "save LikesPosts")
    public Object save(@RequestBody LikesPosts likesPosts){
        try {
            return ReturnT.success(likesPostsRepository.save(likesPosts));
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnT.error("保存失败");
        }

    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    @ApiOperation(value = "delete LikesPosts", notes = "delete LikesPosts")
    public Object delete(int id){
        Optional<LikesPosts> likesPosts=likesPostsRepository.findById(id);
        if(likesPosts.isPresent()){
            likesPostsRepository.deleteById(id);
            return ReturnT.success("删除成功");
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 查询
    */
    @PostMapping("/find")
    @ApiOperation(value = "find LikesPosts by id", notes = "find LikesPosts by id")
    public Object find(int id){
        Optional<LikesPosts> likesPosts=likesPostsRepository.findById(id);
        if(likesPosts.isPresent()){
            return ReturnT.success(likesPosts.get());
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    @ApiOperation(value = "list LikesPosts", notes = "list LikesPosts")
    public Object list(@RequestBody LikesPosts likesPosts,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {

            try {
                //创建匹配器，需要查询条件请修改此处代码
                ExampleMatcher matcher = ExampleMatcher.matchingAll();

                //创建实例
                Example<LikesPosts> example = Example.of(likesPosts, matcher);
                //分页构造
                Pageable pageable = PageRequest.of(pageNumber,pageSize);

                return ReturnT.success(likesPostsRepository.findAll(example, pageable));

            } catch (Exception e) {
                e.printStackTrace();
                return ReturnT.error(e.getMessage());
            }

    }

}
