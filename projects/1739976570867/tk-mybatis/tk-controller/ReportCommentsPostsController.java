package com.hzy.controller;
import com.hzy.entity.ReportCommentsPosts;
import com.hzy.mapper.ReportCommentsPostsMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RestController
@RequestMapping("/reportCommentsPosts")
public class ReportCommentsPostsController {

    @Autowired
    private ReportCommentsPostsMapper reportCommentsPostsMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(ReportCommentsPosts reportCommentsPosts){
        if(reportCommentsPostsMapper.selectCount(reportCommentsPosts)>0){
            reportCommentsPostsMapper.insert(reportCommentsPosts);
        }else{
            reportCommentsPostsMapper.updateByPrimaryKeySelective(reportCommentsPosts);
        }
        return ReturnT.success("新增或编辑成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        if(reportCommentsPostsMapper.selectCount(reportCommentsPosts)>0){
            reportCommentsPostsMapper.deleteByPrimaryKey(id);
            return ReturnT.success("删除成功");
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 查询
    */
    @PostMapping("/find")
    public Object find(int id){
        Optional<ReportCommentsPosts> reportCommentsPosts=reportCommentsPostsMapper.selectOne(id);
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
    public Object list(ReportCommentsPosts reportCommentsPosts,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            //TBC
            return reportCommentsPostsMapper.selectList(reportCommentsPosts);
    }

}
