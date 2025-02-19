package com.hzy.controller;
import com.hzy.entity.PostsLeague;
import com.hzy.repository.PostsLeagueRepository;
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
 * @description posts_league
 * @author capkin
 * @date 2025-02-19
 */

@Slf4j
@Api(tags = "postsLeague")
@CrossOrigin
@RestController
@RequestMapping("/postsLeague")
public class PostsLeagueController {

    @Autowired
    private PostsLeagueRepository postsLeagueRepository;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    @ApiOperation(value = "save PostsLeague", notes = "save PostsLeague")
    public Object save(@RequestBody PostsLeague postsLeague){
        try {
            return ReturnT.success(postsLeagueRepository.save(postsLeague));
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnT.error("保存失败");
        }

    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    @ApiOperation(value = "delete PostsLeague", notes = "delete PostsLeague")
    public Object delete(int id){
        Optional<PostsLeague> postsLeague=postsLeagueRepository.findById(id);
        if(postsLeague.isPresent()){
            postsLeagueRepository.deleteById(id);
            return ReturnT.success("删除成功");
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 查询
    */
    @PostMapping("/find")
    @ApiOperation(value = "find PostsLeague by id", notes = "find PostsLeague by id")
    public Object find(int id){
        Optional<PostsLeague> postsLeague=postsLeagueRepository.findById(id);
        if(postsLeague.isPresent()){
            return ReturnT.success(postsLeague.get());
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    @ApiOperation(value = "list PostsLeague", notes = "list PostsLeague")
    public Object list(@RequestBody PostsLeague postsLeague,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {

            try {
                //创建匹配器，需要查询条件请修改此处代码
                ExampleMatcher matcher = ExampleMatcher.matchingAll();

                //创建实例
                Example<PostsLeague> example = Example.of(postsLeague, matcher);
                //分页构造
                Pageable pageable = PageRequest.of(pageNumber,pageSize);

                return ReturnT.success(postsLeagueRepository.findAll(example, pageable));

            } catch (Exception e) {
                e.printStackTrace();
                return ReturnT.error(e.getMessage());
            }

    }

}
