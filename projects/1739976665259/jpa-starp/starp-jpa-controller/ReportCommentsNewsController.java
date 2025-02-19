package com.hzy.controller;
import com.hzy.entity.ReportCommentsNews;
import com.hzy.repository.ReportCommentsNewsRepository;
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
 * @description report_comments_news
 * @author capkin
 * @date 2025-02-19
 */

@Slf4j
@Api(tags = "reportCommentsNews")
@CrossOrigin
@RestController
@RequestMapping("/reportCommentsNews")
public class ReportCommentsNewsController {

    @Autowired
    private ReportCommentsNewsRepository reportCommentsNewsRepository;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    @ApiOperation(value = "save ReportCommentsNews", notes = "save ReportCommentsNews")
    public Object save(@RequestBody ReportCommentsNews reportCommentsNews){
        try {
            return ReturnT.success(reportCommentsNewsRepository.save(reportCommentsNews));
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnT.error("保存失败");
        }

    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    @ApiOperation(value = "delete ReportCommentsNews", notes = "delete ReportCommentsNews")
    public Object delete(int id){
        Optional<ReportCommentsNews> reportCommentsNews=reportCommentsNewsRepository.findById(id);
        if(reportCommentsNews.isPresent()){
            reportCommentsNewsRepository.deleteById(id);
            return ReturnT.success("删除成功");
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 查询
    */
    @PostMapping("/find")
    @ApiOperation(value = "find ReportCommentsNews by id", notes = "find ReportCommentsNews by id")
    public Object find(int id){
        Optional<ReportCommentsNews> reportCommentsNews=reportCommentsNewsRepository.findById(id);
        if(reportCommentsNews.isPresent()){
            return ReturnT.success(reportCommentsNews.get());
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    @ApiOperation(value = "list ReportCommentsNews", notes = "list ReportCommentsNews")
    public Object list(@RequestBody ReportCommentsNews reportCommentsNews,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {

            try {
                //创建匹配器，需要查询条件请修改此处代码
                ExampleMatcher matcher = ExampleMatcher.matchingAll();

                //创建实例
                Example<ReportCommentsNews> example = Example.of(reportCommentsNews, matcher);
                //分页构造
                Pageable pageable = PageRequest.of(pageNumber,pageSize);

                return ReturnT.success(reportCommentsNewsRepository.findAll(example, pageable));

            } catch (Exception e) {
                e.printStackTrace();
                return ReturnT.error(e.getMessage());
            }

    }

}
