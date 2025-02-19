import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
/**
 * @description team
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private SQLManager sqlManager;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(Team team){
        Team team=sqlManager.unique(Team.class,team.getId());
        if(team!=null){
            sqlManager.updateById(team);
            return ReturnT.success("编辑成功");
        }else{
            sqlManager.insert(team);
            return ReturnT.error("保存成功");
        }
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        Team team=sqlManager.unique(Team.class,id);
        if(team!=null){
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
        Team team=sqlManager.unique(Team.class,id);
        if(team!=null){
            return ReturnT.success(team);
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(Team team,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            List<Team> list = sqlManager.query(Team.class).select();
            return ReturnT.success(list);
    }

}
