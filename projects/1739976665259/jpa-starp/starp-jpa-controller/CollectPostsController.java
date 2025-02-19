package com.hzy.controller;
import com.hzy.entity.CollectPosts;
import com.hzy.repository.CollectPostsRepository;
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
 * @description collect_posts
 * @author capkin
 * @date 2025-02-19
 */

@Slf4j
@Api(tags = "collectPosts")
@CrossOrigin
@RestController
@RequestMapping("/collectPosts")
public class CollectPostsController {

    @Autowired
    private CollectPostsRepository collectPostsRepository;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    @ApiOperation(value = "save CollectPosts", notes = "save CollectPosts")
    public Object save(@RequestBody CollectPosts collectPosts){
        try {
            return ReturnT.success(collectPostsRepository.save(collectPosts));
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnT.error("保存失败");
        }

    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    @ApiOperation(value = "delete CollectPosts", notes = "delete CollectPosts")
    public Object delete(int id){
        Optional<CollectPosts> collectPosts=collectPostsRepository.findById(id);
        if(collectPosts.isPresent()){
            collectPostsRepository.deleteById(id);
            return ReturnT.success("删除成功");
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 查询
    */
    @PostMapping("/find")
    @ApiOperation(value = "find CollectPosts by id", notes = "find CollectPosts by id")
    public Object find(int id){
        Optional<CollectPosts> collectPosts=collectPostsRepository.findById(id);
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
    @ApiOperation(value = "list CollectPosts", notes = "list CollectPosts")
    public Object list(@RequestBody CollectPosts collectPosts,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {

            try {
                //创建匹配器，需要查询条件请修改此处代码
                ExampleMatcher matcher = ExampleMatcher.matchingAll();

                //创建实例
                Example<CollectPosts> example = Example.of(collectPosts, matcher);
                //分页构造
                Pageable pageable = PageRequest.of(pageNumber,pageSize);

                return ReturnT.success(collectPostsRepository.findAll(example, pageable));

            } catch (Exception e) {
                e.printStackTrace();
                return ReturnT.error(e.getMessage());
            }

    }

}
