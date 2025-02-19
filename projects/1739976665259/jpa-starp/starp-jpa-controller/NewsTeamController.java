package com.hzy.controller;
import com.hzy.entity.NewsTeam;
import com.hzy.repository.NewsTeamRepository;
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
 * @description news_team
 * @author capkin
 * @date 2025-02-19
 */

@Slf4j
@Api(tags = "newsTeam")
@CrossOrigin
@RestController
@RequestMapping("/newsTeam")
public class NewsTeamController {

    @Autowired
    private NewsTeamRepository newsTeamRepository;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    @ApiOperation(value = "save NewsTeam", notes = "save NewsTeam")
    public Object save(@RequestBody NewsTeam newsTeam){
        try {
            return ReturnT.success(newsTeamRepository.save(newsTeam));
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnT.error("保存失败");
        }

    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    @ApiOperation(value = "delete NewsTeam", notes = "delete NewsTeam")
    public Object delete(int id){
        Optional<NewsTeam> newsTeam=newsTeamRepository.findById(id);
        if(newsTeam.isPresent()){
            newsTeamRepository.deleteById(id);
            return ReturnT.success("删除成功");
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 查询
    */
    @PostMapping("/find")
    @ApiOperation(value = "find NewsTeam by id", notes = "find NewsTeam by id")
    public Object find(int id){
        Optional<NewsTeam> newsTeam=newsTeamRepository.findById(id);
        if(newsTeam.isPresent()){
            return ReturnT.success(newsTeam.get());
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    @ApiOperation(value = "list NewsTeam", notes = "list NewsTeam")
    public Object list(@RequestBody NewsTeam newsTeam,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {

            try {
                //创建匹配器，需要查询条件请修改此处代码
                ExampleMatcher matcher = ExampleMatcher.matchingAll();

                //创建实例
                Example<NewsTeam> example = Example.of(newsTeam, matcher);
                //分页构造
                Pageable pageable = PageRequest.of(pageNumber,pageSize);

                return ReturnT.success(newsTeamRepository.findAll(example, pageable));

            } catch (Exception e) {
                e.printStackTrace();
                return ReturnT.error(e.getMessage());
            }

    }

}
