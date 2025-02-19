package com.hzy.controller;
import com.alibaba.fastjson.JSON;
import com.hzy.entity.Users;
import com.hzy.mapper.UsersMapper;
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
* @description users控制器
* @author capkin
* @date 2025-02-19
*/
@Slf4j
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersMapper usersMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(@RequestBody Users users){
        log.info("users:"+JSON.toJSONString(users));
        Users oldUsers = usersMapper.selectOne(new QueryWrapper<Users>().eq("users_id",users.getUsersId()));
        users.setUpdateTime(new Date());
        if(oldUsers!=null){
            usersMapper.updateById(users);
        }else{
        if(usersMapper.selectOne(new QueryWrapper<Users>().eq("users_name",users.getUsersName()))!=null){
            return ReturnT.error("保存失败，名字重复");
        }
        users.setCreateTime(new Date());
        usersMapper.insert(users);
        }
        return ReturnT.success("保存成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
    Users users = usersMapper.selectOne(new QueryWrapper<Users>().eq("users_id",id));
        if(users!=null){
            usersMapper.deleteById(id);
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
    Users users = usersMapper.selectOne(new QueryWrapper<Users>().eq("users_id",id));
        if(users!=null){
            return ReturnT.success(users);
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
        Page<Users> buildPage = new Page<Users>(page,limit);
        //条件构造器
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        if(StringUtils.isNotEmpty(searchParams)&&JSON.isValid(searchParams)) {
            Users users = JSON.parseObject(searchParams, Users.class);
            queryWrapper.eq(StringUtils.isNoneEmpty(users.getUsersName()), "users_name", users.getUsersName());
        }
        //执行分页
        IPage<Users> pageList = usersMapper.selectPage(buildPage, queryWrapper);
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
        Users queryParamDTO = JSON.parseObject(searchParams, Users.class);
        //专用DTO模式
        //QueryParamDTO queryParamDTO = JSON.parseObject(searchParams, QueryParamDTO.class);
        //queryParamDTO.setPage((page - 1)* limit);
        //queryParamDTO.setLimit(limit);
        //(page - 1) * limit, limit
        List<Users> itemList = usersMapper.pageAll(queryParamDTO,(page - 1)* limit,limit);
        Integer itemCount = usersMapper.countAll(queryParamDTO);
        //返回结果
        return ReturnT.success.PAGE(itemList,itemCount);
    }*/
    @GetMapping("/list")
    public ModelAndView listPage(){
        return new ModelAndView("users-list");
    }

    @GetMapping("/edit")
    public ModelAndView editPage(int id){
        Users users = usersMapper.selectOne(new QueryWrapper<Users>().eq("users_id",id));
        return new ModelAndView("users-edit","users",users);
    }

    /**
    * 发布/暂停(如不需要请屏蔽)
    */
    @PostMapping("/publish")
    public Object publish(int id,Integer status){
        Users users = usersMapper.selectOne(new QueryWrapper<Users>().eq("users_id",id));
        if(users!=null){
            users.setUpdateTime(new Date());
            users.setStatus(status);
            usersMapper.updateById(users);
            return ReturnT.success((status==1)?"已发布":"已暂停");
        }else if(status.equals(users.getStatus())){
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



