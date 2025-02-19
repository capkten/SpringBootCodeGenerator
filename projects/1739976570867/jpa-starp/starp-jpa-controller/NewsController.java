package com.hzy.controller;
import com.hzy.entity.News;
import com.hzy.repository.NewsRepository;
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
 * @description news
 * @author capkin
 * @date 2025-02-19
 */

@Slf4j
@Api(tags = "news")
@CrossOrigin
@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    @ApiOperation(value = "save News", notes = "save News")
    public Object save(@RequestBody News news){
        try {
            return ReturnT.success(newsRepository.save(news));
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnT.error("保存失败");
        }

    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    @ApiOperation(value = "delete News", notes = "delete News")
    public Object delete(int id){
        Optional<News> news=newsRepository.findById(id);
        if(news.isPresent()){
            newsRepository.deleteById(id);
            return ReturnT.success("删除成功");
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 查询
    */
    @PostMapping("/find")
    @ApiOperation(value = "find News by id", notes = "find News by id")
    public Object find(int id){
        Optional<News> news=newsRepository.findById(id);
        if(news.isPresent()){
            return ReturnT.success(news.get());
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    @ApiOperation(value = "list News", notes = "list News")
    public Object list(@RequestBody News news,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {

            try {
                //创建匹配器，需要查询条件请修改此处代码
                ExampleMatcher matcher = ExampleMatcher.matchingAll();

                //创建实例
                Example<News> example = Example.of(news, matcher);
                //分页构造
                Pageable pageable = PageRequest.of(pageNumber,pageSize);

                return ReturnT.success(newsRepository.findAll(example, pageable));

            } catch (Exception e) {
                e.printStackTrace();
                return ReturnT.error(e.getMessage());
            }

    }

}
