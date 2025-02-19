package com.hzy.controller;
import com.hzy.entity.League;
import com.hzy.mapper.LeagueMapper;
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
 * @description league
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/league")
public class LeagueController {

    @Autowired
    private LeagueMapper leagueMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(League league){
        if(leagueMapper.selectCount(league)>0){
            leagueMapper.insert(league);
        }else{
            leagueMapper.updateByPrimaryKeySelective(league);
        }
        return ReturnT.success("新增或编辑成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        if(leagueMapper.selectCount(league)>0){
            leagueMapper.deleteByPrimaryKey(id);
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
        Optional<League> league=leagueMapper.selectOne(id);
        if(league.isPresent()){
            return ReturnT.success(league.get());
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(League league,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            //TBC
            return leagueMapper.selectList(league);
    }

}
