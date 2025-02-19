import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
/**
 * @description league
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/league")
public class LeagueController {

    @Autowired
    private SQLManager sqlManager;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(League league){
        League league=sqlManager.unique(League.class,league.getId());
        if(league!=null){
            sqlManager.updateById(league);
            return ReturnT.success("编辑成功");
        }else{
            sqlManager.insert(league);
            return ReturnT.error("保存成功");
        }
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        League league=sqlManager.unique(League.class,id);
        if(league!=null){
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
        League league=sqlManager.unique(League.class,id);
        if(league!=null){
            return ReturnT.success(league);
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(League league,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            List<League> list = sqlManager.query(League.class).select();
            return ReturnT.success(list);
    }

}
