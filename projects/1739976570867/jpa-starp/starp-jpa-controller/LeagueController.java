package com.hzy.controller;
import com.hzy.entity.League;
import com.hzy.repository.LeagueRepository;
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
 * @description league
 * @author capkin
 * @date 2025-02-19
 */

@Slf4j
@Api(tags = "league")
@CrossOrigin
@RestController
@RequestMapping("/league")
public class LeagueController {

    @Autowired
    private LeagueRepository leagueRepository;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    @ApiOperation(value = "save League", notes = "save League")
    public Object save(@RequestBody League league){
        try {
            return ReturnT.success(leagueRepository.save(league));
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnT.error("保存失败");
        }

    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    @ApiOperation(value = "delete League", notes = "delete League")
    public Object delete(int id){
        Optional<League> league=leagueRepository.findById(id);
        if(league.isPresent()){
            leagueRepository.deleteById(id);
            return ReturnT.success("删除成功");
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 查询
    */
    @PostMapping("/find")
    @ApiOperation(value = "find League by id", notes = "find League by id")
    public Object find(int id){
        Optional<League> league=leagueRepository.findById(id);
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
    @ApiOperation(value = "list League", notes = "list League")
    public Object list(@RequestBody League league,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {

            try {
                //创建匹配器，需要查询条件请修改此处代码
                ExampleMatcher matcher = ExampleMatcher.matchingAll();

                //创建实例
                Example<League> example = Example.of(league, matcher);
                //分页构造
                Pageable pageable = PageRequest.of(pageNumber,pageSize);

                return ReturnT.success(leagueRepository.findAll(example, pageable));

            } catch (Exception e) {
                e.printStackTrace();
                return ReturnT.error(e.getMessage());
            }

    }

}
