package com.hzy.controller;
import com.alibaba.fastjson.JSON;
import com.hzy.entity.Admin;
import com.hzy.mapper.AdminMapper;
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
* @description admin控制器
* @author capkin
* @date 2025-02-19
*/
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminMapper adminMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(@RequestBody Admin admin){
        log.info("admin:"+JSON.toJSONString(admin));
        Admin oldAdmin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("admin_id",admin.getAdminId()));
        admin.setUpdateTime(new Date());
        if(oldAdmin!=null){
            adminMapper.updateById(admin);
        }else{
        if(adminMapper.selectOne(new QueryWrapper<Admin>().eq("admin_name",admin.getAdminName()))!=null){
            return ReturnT.error("保存失败，名字重复");
        }
        admin.setCreateTime(new Date());
        adminMapper.insert(admin);
        }
        return ReturnT.success("保存成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
    Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("admin_id",id));
        if(admin!=null){
            adminMapper.deleteById(id);
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
    Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("admin_id",id));
        if(admin!=null){
            return ReturnT.success(admin);
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
        Page<Admin> buildPage = new Page<Admin>(page,limit);
        //条件构造器
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<Admin>();
        if(StringUtils.isNotEmpty(searchParams)&&JSON.isValid(searchParams)) {
            Admin admin = JSON.parseObject(searchParams, Admin.class);
            queryWrapper.eq(StringUtils.isNoneEmpty(admin.getAdminName()), "admin_name", admin.getAdminName());
        }
        //执行分页
        IPage<Admin> pageList = adminMapper.selectPage(buildPage, queryWrapper);
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
        Admin queryParamDTO = JSON.parseObject(searchParams, Admin.class);
        //专用DTO模式
        //QueryParamDTO queryParamDTO = JSON.parseObject(searchParams, QueryParamDTO.class);
        //queryParamDTO.setPage((page - 1)* limit);
        //queryParamDTO.setLimit(limit);
        //(page - 1) * limit, limit
        List<Admin> itemList = adminMapper.pageAll(queryParamDTO,(page - 1)* limit,limit);
        Integer itemCount = adminMapper.countAll(queryParamDTO);
        //返回结果
        return ReturnT.success.PAGE(itemList,itemCount);
    }*/
    @GetMapping("/list")
    public ModelAndView listPage(){
        return new ModelAndView("admin-list");
    }

    @GetMapping("/edit")
    public ModelAndView editPage(int id){
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("admin_id",id));
        return new ModelAndView("admin-edit","admin",admin);
    }

    /**
    * 发布/暂停(如不需要请屏蔽)
    */
    @PostMapping("/publish")
    public Object publish(int id,Integer status){
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("admin_id",id));
        if(admin!=null){
            admin.setUpdateTime(new Date());
            admin.setStatus(status);
            adminMapper.updateById(admin);
            return ReturnT.success((status==1)?"已发布":"已暂停");
        }else if(status.equals(admin.getStatus())){
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



