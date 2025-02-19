package com.hzy.controller;
import com.hzy.entity.ReportCommentsPosts;
import com.hzy.repository.ReportCommentsPostsRepository;
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
 * @description report_comments_posts
 * @author capkin
 * @date 2025-02-19
 */

@Slf4j
@Api(tags = "reportCommentsPosts")
@CrossOrigin
@RestController
@RequestMapping("/reportCommentsPosts")
public class ReportCommentsPostsController {

    @Autowired
    private ReportCommentsPostsRepository reportCommentsPostsRepository;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    @ApiOperation(value = "save ReportCommentsPosts", notes = "save ReportCommentsPosts")
    public Object save(@RequestBody ReportCommentsPosts reportCommentsPosts){
        try {
            return ReturnT.success(reportCommentsPostsRepository.save(reportCommentsPosts));
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnT.error("保存失败");
        }

    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    @ApiOperation(value = "delete ReportCommentsPosts", notes = "delete ReportCommentsPosts")
    public Object delete(int id){
        Optional<ReportCommentsPosts> reportCommentsPosts=reportCommentsPostsRepository.findById(id);
        if(reportCommentsPosts.isPresent()){
            reportCommentsPostsRepository.deleteById(id);
            return ReturnT.success("删除成功");
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 查询
    */
    @PostMapping("/find")
    @ApiOperation(value = "find ReportCommentsPosts by id", notes = "find ReportCommentsPosts by id")
    public Object find(int id){
        Optional<ReportCommentsPosts> reportCommentsPosts=reportCommentsPostsRepository.findById(id);
        if(reportCommentsPosts.isPresent()){
            return ReturnT.success(reportCommentsPosts.get());
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    @ApiOperation(value = "list ReportCommentsPosts", notes = "list ReportCommentsPosts")
    public Object list(@RequestBody ReportCommentsPosts reportCommentsPosts,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {

            try {
                //创建匹配器，需要查询条件请修改此处代码
                ExampleMatcher matcher = ExampleMatcher.matchingAll();

                //创建实例
                Example<ReportCommentsPosts> example = Example.of(reportCommentsPosts, matcher);
                //分页构造
                Pageable pageable = PageRequest.of(pageNumber,pageSize);

                return ReturnT.success(reportCommentsPostsRepository.findAll(example, pageable));

            } catch (Exception e) {
                e.printStackTrace();
                return ReturnT.error(e.getMessage());
            }

    }

}
