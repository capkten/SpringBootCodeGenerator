package com.hzy.controller;
import com.hzy.entity.PostsLeague;
import com.hzy.mapper.PostsLeagueMapper;
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
 * @description posts_league
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/postsLeague")
public class PostsLeagueController {

    @Autowired
    private PostsLeagueMapper postsLeagueMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(PostsLeague postsLeague){
        if(postsLeagueMapper.selectCount(postsLeague)>0){
            postsLeagueMapper.insert(postsLeague);
        }else{
            postsLeagueMapper.updateByPrimaryKeySelective(postsLeague);
        }
        return ReturnT.success("新增或编辑成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        if(postsLeagueMapper.selectCount(postsLeague)>0){
            postsLeagueMapper.deleteByPrimaryKey(id);
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
        Optional<PostsLeague> postsLeague=postsLeagueMapper.selectOne(id);
        if(postsLeague.isPresent()){
            return ReturnT.success(postsLeague.get());
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(PostsLeague postsLeague,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            //TBC
            return postsLeagueMapper.selectList(postsLeague);
    }

}
