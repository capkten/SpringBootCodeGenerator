package com.hzy.controller;
import com.hzy.entity.ReportCommentsNews;
import com.hzy.mapper.ReportCommentsNewsMapper;
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
 * @description report_comments_news
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/reportCommentsNews")
public class ReportCommentsNewsController {

    @Autowired
    private ReportCommentsNewsMapper reportCommentsNewsMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(ReportCommentsNews reportCommentsNews){
        if(reportCommentsNewsMapper.selectCount(reportCommentsNews)>0){
            reportCommentsNewsMapper.insert(reportCommentsNews);
        }else{
            reportCommentsNewsMapper.updateByPrimaryKeySelective(reportCommentsNews);
        }
        return ReturnT.success("新增或编辑成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        if(reportCommentsNewsMapper.selectCount(reportCommentsNews)>0){
            reportCommentsNewsMapper.deleteByPrimaryKey(id);
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
        Optional<ReportCommentsNews> reportCommentsNews=reportCommentsNewsMapper.selectOne(id);
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
    public Object list(ReportCommentsNews reportCommentsNews,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            //TBC
            return reportCommentsNewsMapper.selectList(reportCommentsNews);
    }

}
