package com.hzy.controller;
import com.hzy.entity.LikesNews;
import com.hzy.repository.LikesNewsRepository;
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
 * @description likes_news
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/likesNews")
public class LikesNewsController {

    @Autowired
    private LikesNewsRepository likesNewsRepository;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(LikesNews likesNews){
        return likesNewsRepository.save(likesNews);
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        Optional<LikesNews> likesNews=likesNewsRepository.findById(id);
        if(likesNews.isPresent()){
            likesNewsRepository.deleteById(id);
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
        Optional<LikesNews> likesNews=likesNewsRepository.findById(id);
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

            //创建匹配器，需要查询条件请修改此处代码
            ExampleMatcher matcher = ExampleMatcher.matchingAll();

            //创建实例
            Example<LikesNews> example = Example.of(likesNews, matcher);
            //分页构造
            Pageable pageable = PageRequest.of(pageNumber,pageSize);

            return likesNewsRepository.findAll(example, pageable);
    }

}
