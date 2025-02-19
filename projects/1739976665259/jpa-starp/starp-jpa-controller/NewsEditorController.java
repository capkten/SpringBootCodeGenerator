package com.hzy.controller;
import com.hzy.entity.NewsEditor;
import com.hzy.repository.NewsEditorRepository;
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
 * @description news_editor
 * @author capkin
 * @date 2025-02-19
 */

@Slf4j
@Api(tags = "newsEditor")
@CrossOrigin
@RestController
@RequestMapping("/newsEditor")
public class NewsEditorController {

    @Autowired
    private NewsEditorRepository newsEditorRepository;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    @ApiOperation(value = "save NewsEditor", notes = "save NewsEditor")
    public Object save(@RequestBody NewsEditor newsEditor){
        try {
            return ReturnT.success(newsEditorRepository.save(newsEditor));
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnT.error("保存失败");
        }

    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    @ApiOperation(value = "delete NewsEditor", notes = "delete NewsEditor")
    public Object delete(int id){
        Optional<NewsEditor> newsEditor=newsEditorRepository.findById(id);
        if(newsEditor.isPresent()){
            newsEditorRepository.deleteById(id);
            return ReturnT.success("删除成功");
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 查询
    */
    @PostMapping("/find")
    @ApiOperation(value = "find NewsEditor by id", notes = "find NewsEditor by id")
    public Object find(int id){
        Optional<NewsEditor> newsEditor=newsEditorRepository.findById(id);
        if(newsEditor.isPresent()){
            return ReturnT.success(newsEditor.get());
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    @ApiOperation(value = "list NewsEditor", notes = "list NewsEditor")
    public Object list(@RequestBody NewsEditor newsEditor,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {

            try {
                //创建匹配器，需要查询条件请修改此处代码
                ExampleMatcher matcher = ExampleMatcher.matchingAll();

                //创建实例
                Example<NewsEditor> example = Example.of(newsEditor, matcher);
                //分页构造
                Pageable pageable = PageRequest.of(pageNumber,pageSize);

                return ReturnT.success(newsEditorRepository.findAll(example, pageable));

            } catch (Exception e) {
                e.printStackTrace();
                return ReturnT.error(e.getMessage());
            }

    }

}
