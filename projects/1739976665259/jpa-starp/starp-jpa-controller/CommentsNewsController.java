package com.hzy.controller;
import com.hzy.entity.CommentsNews;
import com.hzy.repository.CommentsNewsRepository;
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
 * @description comments_news
 * @author capkin
 * @date 2025-02-19
 */

@Slf4j
@Api(tags = "commentsNews")
@CrossOrigin
@RestController
@RequestMapping("/commentsNews")
public class CommentsNewsController {

    @Autowired
    private CommentsNewsRepository commentsNewsRepository;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    @ApiOperation(value = "save CommentsNews", notes = "save CommentsNews")
    public Object save(@RequestBody CommentsNews commentsNews){
        try {
            return ReturnT.success(commentsNewsRepository.save(commentsNews));
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnT.error("保存失败");
        }

    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    @ApiOperation(value = "delete CommentsNews", notes = "delete CommentsNews")
    public Object delete(int id){
        Optional<CommentsNews> commentsNews=commentsNewsRepository.findById(id);
        if(commentsNews.isPresent()){
            commentsNewsRepository.deleteById(id);
            return ReturnT.success("删除成功");
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 查询
    */
    @PostMapping("/find")
    @ApiOperation(value = "find CommentsNews by id", notes = "find CommentsNews by id")
    public Object find(int id){
        Optional<CommentsNews> commentsNews=commentsNewsRepository.findById(id);
        if(commentsNews.isPresent()){
            return ReturnT.success(commentsNews.get());
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    @ApiOperation(value = "list CommentsNews", notes = "list CommentsNews")
    public Object list(@RequestBody CommentsNews commentsNews,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {

            try {
                //创建匹配器，需要查询条件请修改此处代码
                ExampleMatcher matcher = ExampleMatcher.matchingAll();

                //创建实例
                Example<CommentsNews> example = Example.of(commentsNews, matcher);
                //分页构造
                Pageable pageable = PageRequest.of(pageNumber,pageSize);

                return ReturnT.success(commentsNewsRepository.findAll(example, pageable));

            } catch (Exception e) {
                e.printStackTrace();
                return ReturnT.error(e.getMessage());
            }

    }

}
