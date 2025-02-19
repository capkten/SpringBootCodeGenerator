package com.hzy.controller;
import com.hzy.entity.ReportPosts;
import com.hzy.mapper.ReportPostsMapper;
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
 * @description report_posts
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/reportPosts")
public class ReportPostsController {

    @Autowired
    private ReportPostsMapper reportPostsMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(ReportPosts reportPosts){
        if(reportPostsMapper.selectCount(reportPosts)>0){
            reportPostsMapper.insert(reportPosts);
        }else{
            reportPostsMapper.updateByPrimaryKeySelective(reportPosts);
        }
        return ReturnT.success("新增或编辑成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        if(reportPostsMapper.selectCount(reportPosts)>0){
            reportPostsMapper.deleteByPrimaryKey(id);
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
        Optional<ReportPosts> reportPosts=reportPostsMapper.selectOne(id);
        if(reportPosts.isPresent()){
            return ReturnT.success(reportPosts.get());
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(ReportPosts reportPosts,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            //TBC
            return reportPostsMapper.selectList(reportPosts);
    }

}
