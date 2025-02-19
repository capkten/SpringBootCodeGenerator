package com.hzy.controller;
import com.hzy.entity.LikesNews;
import com.hzy.mapper.LikesNewsMapper;
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
 * @description likes_news
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/likesNews")
public class LikesNewsController {

    @Autowired
    private LikesNewsMapper likesNewsMapper;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(LikesNews likesNews){
        if(likesNewsMapper.selectCount(likesNews)>0){
            likesNewsMapper.insert(likesNews);
        }else{
            likesNewsMapper.updateByPrimaryKeySelective(likesNews);
        }
        return ReturnT.success("新增或编辑成功");
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        if(likesNewsMapper.selectCount(likesNews)>0){
            likesNewsMapper.deleteByPrimaryKey(id);
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
        Optional<LikesNews> likesNews=likesNewsMapper.selectOne(id);
        if(likesNews.isPresent()){
            return ReturnT.success(likesNews.get());
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(LikesNews likesNews,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            //TBC
            return likesNewsMapper.selectList(likesNews);
    }

}
