package com.hzy.controller;
import com.hzy.entity.NewsEditor;
import com.hzy.mapper.NewsEditorMapper;
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
 * @description news_editor
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/newsEditor")
public class NewsEditorController {

    @Autowired
    private NewsEditorMapper newsEditorMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(NewsEditor newsEditor){
        if(newsEditorMapper.selectCount(newsEditor)>0){
            newsEditorMapper.insert(newsEditor);
        }else{
            newsEditorMapper.updateByPrimaryKeySelective(newsEditor);
        }
        return ReturnT.success("新增或编辑成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        if(newsEditorMapper.selectCount(newsEditor)>0){
            newsEditorMapper.deleteByPrimaryKey(id);
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
        Optional<NewsEditor> newsEditor=newsEditorMapper.selectOne(id);
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
    public Object list(NewsEditor newsEditor,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            //TBC
            return newsEditorMapper.selectList(newsEditor);
    }

}
