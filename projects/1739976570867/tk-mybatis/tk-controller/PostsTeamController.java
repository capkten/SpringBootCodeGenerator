package com.hzy.controller;
import com.hzy.entity.PostsTeam;
import com.hzy.mapper.PostsTeamMapper;
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
 * @description posts_team
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/postsTeam")
public class PostsTeamController {

    @Autowired
    private PostsTeamMapper postsTeamMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(PostsTeam postsTeam){
        if(postsTeamMapper.selectCount(postsTeam)>0){
            postsTeamMapper.insert(postsTeam);
        }else{
            postsTeamMapper.updateByPrimaryKeySelective(postsTeam);
        }
        return ReturnT.success("新增或编辑成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        if(postsTeamMapper.selectCount(postsTeam)>0){
            postsTeamMapper.deleteByPrimaryKey(id);
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
        Optional<PostsTeam> postsTeam=postsTeamMapper.selectOne(id);
        if(postsTeam.isPresent()){
            return ReturnT.success(postsTeam.get());
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(PostsTeam postsTeam,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            //TBC
            return postsTeamMapper.selectList(postsTeam);
    }

}
