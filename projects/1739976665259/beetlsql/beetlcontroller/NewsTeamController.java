import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
/**
 * @description news_team
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/newsTeam")
public class NewsTeamController {

    @Autowired
    private SQLManager sqlManager;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(NewsTeam newsTeam){
        NewsTeam newsTeam=sqlManager.unique(NewsTeam.class,newsTeam.getId());
        if(newsTeam!=null){
            sqlManager.updateById(newsTeam);
            return ReturnT.success("编辑成功");
        }else{
            sqlManager.insert(newsTeam);
            return ReturnT.error("保存成功");
        }
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        NewsTeam newsTeam=sqlManager.unique(NewsTeam.class,id);
        if(newsTeam!=null){
            sqlManager.deleteById(id);
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
        NewsTeam newsTeam=sqlManager.unique(NewsTeam.class,id);
        if(newsTeam!=null){
            return ReturnT.success(newsTeam);
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(NewsTeam newsTeam,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            List<NewsTeam> list = sqlManager.query(NewsTeam.class).select();
            return ReturnT.success(list);
    }

}
