package com.hzy.controller;
import com.hzy.entity.NewsTeam;
import com.hzy.mapper.NewsTeamMapper;
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
 * @description news_team
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/newsTeam")
public class NewsTeamController {

    @Autowired
    private NewsTeamMapper newsTeamMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(NewsTeam newsTeam){
        if(newsTeamMapper.selectCount(newsTeam)>0){
            newsTeamMapper.insert(newsTeam);
        }else{
            newsTeamMapper.updateByPrimaryKeySelective(newsTeam);
        }
        return ReturnT.success("新增或编辑成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        if(newsTeamMapper.selectCount(newsTeam)>0){
            newsTeamMapper.deleteByPrimaryKey(id);
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
        Optional<NewsTeam> newsTeam=newsTeamMapper.selectOne(id);
        if(newsTeam.isPresent()){
            return ReturnT.success(newsTeam.get());
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(NewsTeam newsTeam,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            //TBC
            return newsTeamMapper.selectList(newsTeam);
    }

}
