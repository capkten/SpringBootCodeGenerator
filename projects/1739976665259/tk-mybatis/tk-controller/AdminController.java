package com.hzy.controller;
import com.hzy.entity.Admin;
import com.hzy.mapper.AdminMapper;
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
 * @description admin
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminMapper adminMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(Admin admin){
        if(adminMapper.selectCount(admin)>0){
            adminMapper.insert(admin);
        }else{
            adminMapper.updateByPrimaryKeySelective(admin);
        }
        return ReturnT.success("新增或编辑成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        if(adminMapper.selectCount(admin)>0){
            adminMapper.deleteByPrimaryKey(id);
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
        Optional<Admin> admin=adminMapper.selectOne(id);
        if(admin.isPresent()){
            return ReturnT.success(admin.get());
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(Admin admin,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            //TBC
            return adminMapper.selectList(admin);
    }

}
