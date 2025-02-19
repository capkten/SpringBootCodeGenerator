package com.hzy.controller;
import com.hzy.entity.CollectNews;
import com.hzy.repository.CollectNewsRepository;
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
 * @description collect_news
 * @author capkin
 * @date 2025-02-19
 */

@Slf4j
@Api(tags = "collectNews")
@CrossOrigin
@RestController
@RequestMapping("/collectNews")
public class CollectNewsController {

    @Autowired
    private CollectNewsRepository collectNewsRepository;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    @ApiOperation(value = "save CollectNews", notes = "save CollectNews")
    public Object save(@RequestBody CollectNews collectNews){
        try {
            return ReturnT.success(collectNewsRepository.save(collectNews));
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnT.error("保存失败");
        }

    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    @ApiOperation(value = "delete CollectNews", notes = "delete CollectNews")
    public Object delete(int id){
        Optional<CollectNews> collectNews=collectNewsRepository.findById(id);
        if(collectNews.isPresent()){
            collectNewsRepository.deleteById(id);
            return ReturnT.success("删除成功");
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 查询
    */
    @PostMapping("/find")
    @ApiOperation(value = "find CollectNews by id", notes = "find CollectNews by id")
    public Object find(int id){
        Optional<CollectNews> collectNews=collectNewsRepository.findById(id);
        if(collectNews.isPresent()){
            return ReturnT.success(collectNews.get());
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    @ApiOperation(value = "list CollectNews", notes = "list CollectNews")
    public Object list(@RequestBody CollectNews collectNews,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {

            try {
                //创建匹配器，需要查询条件请修改此处代码
                ExampleMatcher matcher = ExampleMatcher.matchingAll();

                //创建实例
                Example<CollectNews> example = Example.of(collectNews, matcher);
                //分页构造
                Pageable pageable = PageRequest.of(pageNumber,pageSize);

                return ReturnT.success(collectNewsRepository.findAll(example, pageable));

            } catch (Exception e) {
                e.printStackTrace();
                return ReturnT.error(e.getMessage());
            }

    }

}
