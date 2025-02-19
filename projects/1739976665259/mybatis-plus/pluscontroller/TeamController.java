package com.hzy.controller;
import com.alibaba.fastjson.JSON;
import com.hzy.entity.Team;
import com.hzy.mapper.TeamMapper;
import com.hzy.util.ReturnT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;
/**
* @description team控制器
* @author capkin
* @date 2025-02-19
*/
@Slf4j
@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamMapper teamMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(@RequestBody Team team){
        log.info("team:"+JSON.toJSONString(team));
        Team oldTeam = teamMapper.selectOne(new QueryWrapper<Team>().eq("team_id",team.getTeamId()));
        team.setUpdateTime(new Date());
        if(oldTeam!=null){
            teamMapper.updateById(team);
        }else{
        if(teamMapper.selectOne(new QueryWrapper<Team>().eq("team_name",team.getTeamName()))!=null){
            return ReturnT.error("保存失败，名字重复");
        }
        team.setCreateTime(new Date());
        teamMapper.insert(team);
        }
        return ReturnT.success("保存成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
    Team team = teamMapper.selectOne(new QueryWrapper<Team>().eq("team_id",id));
        if(team!=null){
            teamMapper.deleteById(id);
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
    Team team = teamMapper.selectOne(new QueryWrapper<Team>().eq("team_id",id));
        if(team!=null){
            return ReturnT.success(team);
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 自动分页查询
    */
    @PostMapping("/list")
    public Object list(String searchParams,
    @RequestParam(required = false, defaultValue = "0") int page,
    @RequestParam(required = false, defaultValue = "10") int limit) {
        log.info("page:"+page+"-limit:"+limit+"-json:"+ JSON.toJSONString(searchParams));
        //分页构造器
        Page<Team> buildPage = new Page<Team>(page,limit);
        //条件构造器
        QueryWrapper<Team> queryWrapper = new QueryWrapper<Team>();
        if(StringUtils.isNotEmpty(searchParams)&&JSON.isValid(searchParams)) {
            Team team = JSON.parseObject(searchParams, Team.class);
            queryWrapper.eq(StringUtils.isNoneEmpty(team.getTeamName()), "team_name", team.getTeamName());
        }
        //执行分页
        IPage<Team> pageList = teamMapper.selectPage(buildPage, queryWrapper);
        //返回结果
        return .PAGE(pageList.getRecords(),pageList.getTotal());
    }
    /**
    * 手工分页查询(按需使用)
    */
    /*@PostMapping("/list2")
    public Object list2(String searchParams,
    @RequestParam(required = false, defaultValue = "0") int page,
    @RequestParam(required = false, defaultValue = "10") int limit) {
        log.info("searchParams:"+ JSON.toJSONString(searchParams));
        //通用模式
        Team queryParamDTO = JSON.parseObject(searchParams, Team.class);
        //专用DTO模式
        //QueryParamDTO queryParamDTO = JSON.parseObject(searchParams, QueryParamDTO.class);
        //queryParamDTO.setPage((page - 1)* limit);
        //queryParamDTO.setLimit(limit);
        //(page - 1) * limit, limit
        List<Team> itemList = teamMapper.pageAll(queryParamDTO,(page - 1)* limit,limit);
        Integer itemCount = teamMapper.countAll(queryParamDTO);
        //返回结果
        return ReturnT.success.PAGE(itemList,itemCount);
    }*/
    @GetMapping("/list")
    public ModelAndView listPage(){
        return new ModelAndView("team-list");
    }

    @GetMapping("/edit")
    public ModelAndView editPage(int id){
        Team team = teamMapper.selectOne(new QueryWrapper<Team>().eq("team_id",id));
        return new ModelAndView("team-edit","team",team);
    }

    /**
    * 发布/暂停(如不需要请屏蔽)
    */
    @PostMapping("/publish")
    public Object publish(int id,Integer status){
        Team team = teamMapper.selectOne(new QueryWrapper<Team>().eq("team_id",id));
        if(team!=null){
            team.setUpdateTime(new Date());
            team.setStatus(status);
            teamMapper.updateById(team);
            return ReturnT.success((status==1)?"已发布":"已暂停");
        }else if(status.equals(team.getStatus())){
            return ReturnT.error("状态不正确");
        }else{
            return ReturnT.error();
        }
    }

    /**
    * 执行(如不需要请屏蔽)
    */
    @PostMapping("/execute")
    public Object execute(){
        return ReturnT.success;
    }
}
}



