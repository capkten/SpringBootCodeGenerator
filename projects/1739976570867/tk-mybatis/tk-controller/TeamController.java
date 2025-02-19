package com.hzy.controller;
import com.hzy.entity.Team;
import com.hzy.mapper.TeamMapper;
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
 * @description team
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamMapper teamMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(Team team){
        if(teamMapper.selectCount(team)>0){
            teamMapper.insert(team);
        }else{
            teamMapper.updateByPrimaryKeySelective(team);
        }
        return ReturnT.success("新增或编辑成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        if(teamMapper.selectCount(team)>0){
            teamMapper.deleteByPrimaryKey(id);
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
        Optional<Team> team=teamMapper.selectOne(id);
        if(team.isPresent()){
            return ReturnT.success(team.get());
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(Team team,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            //TBC
            return teamMapper.selectList(team);
    }

}
