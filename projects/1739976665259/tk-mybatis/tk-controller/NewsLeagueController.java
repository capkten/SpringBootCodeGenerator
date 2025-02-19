package com.hzy.controller;
import com.hzy.entity.NewsLeague;
import com.hzy.mapper.NewsLeagueMapper;
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
 * @description news_league
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/newsLeague")
public class NewsLeagueController {

    @Autowired
    private NewsLeagueMapper newsLeagueMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(NewsLeague newsLeague){
        if(newsLeagueMapper.selectCount(newsLeague)>0){
            newsLeagueMapper.insert(newsLeague);
        }else{
            newsLeagueMapper.updateByPrimaryKeySelective(newsLeague);
        }
        return ReturnT.success("新增或编辑成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        if(newsLeagueMapper.selectCount(newsLeague)>0){
            newsLeagueMapper.deleteByPrimaryKey(id);
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
        Optional<NewsLeague> newsLeague=newsLeagueMapper.selectOne(id);
        if(newsLeague.isPresent()){
            return ReturnT.success(newsLeague.get());
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(NewsLeague newsLeague,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            //TBC
            return newsLeagueMapper.selectList(newsLeague);
    }

}
