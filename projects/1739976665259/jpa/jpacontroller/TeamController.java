package com.hzy.controller;
import com.hzy.entity.Team;
import com.hzy.repository.TeamRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
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
    private TeamRepository teamRepository;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(Team team){
        return teamRepository.save(team);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        Optional<Team> team=teamRepository.findById(id);
        if(team.isPresent()){
            teamRepository.deleteById(id);
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
        Optional<Team> team=teamRepository.findById(id);
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

            //创建匹配器，需要查询条件请修改此处代码
            ExampleMatcher matcher = ExampleMatcher.matchingAll();

            //创建实例
            Example<Team> example = Example.of(team, matcher);
            //分页构造
            Pageable pageable = PageRequest.of(pageNumber,pageSize);

            return teamRepository.findAll(example, pageable);
    }

}
