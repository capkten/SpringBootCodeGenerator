package com.hzy.controller;
import com.hzy.entity.CommentsNews;
import com.hzy.mapper.CommentsNewsMapper;
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
 * @description comments_news
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/commentsNews")
public class CommentsNewsController {

    @Autowired
    private CommentsNewsMapper commentsNewsMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(CommentsNews commentsNews){
        if(commentsNewsMapper.selectCount(commentsNews)>0){
            commentsNewsMapper.insert(commentsNews);
        }else{
            commentsNewsMapper.updateByPrimaryKeySelective(commentsNews);
        }
        return ReturnT.success("新增或编辑成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        if(commentsNewsMapper.selectCount(commentsNews)>0){
            commentsNewsMapper.deleteByPrimaryKey(id);
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
        Optional<CommentsNews> commentsNews=commentsNewsMapper.selectOne(id);
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
    public Object list(CommentsNews commentsNews,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            //TBC
            return commentsNewsMapper.selectList(commentsNews);
    }

}
