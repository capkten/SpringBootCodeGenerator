package com.hzy.controller;
import com.hzy.entity.CollectNews;
import com.hzy.mapper.CollectNewsMapper;
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
 * @description collect_news
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/collectNews")
public class CollectNewsController {

    @Autowired
    private CollectNewsMapper collectNewsMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(CollectNews collectNews){
        if(collectNewsMapper.selectCount(collectNews)>0){
            collectNewsMapper.insert(collectNews);
        }else{
            collectNewsMapper.updateByPrimaryKeySelective(collectNews);
        }
        return ReturnT.success("新增或编辑成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        if(collectNewsMapper.selectCount(collectNews)>0){
            collectNewsMapper.deleteByPrimaryKey(id);
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
        Optional<CollectNews> collectNews=collectNewsMapper.selectOne(id);
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
    public Object list(CollectNews collectNews,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            //TBC
            return collectNewsMapper.selectList(collectNews);
    }

}
