import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
/**
 * @description news_league
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/newsLeague")
public class NewsLeagueController {

    @Autowired
    private SQLManager sqlManager;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(NewsLeague newsLeague){
        NewsLeague newsLeague=sqlManager.unique(NewsLeague.class,newsLeague.getId());
        if(newsLeague!=null){
            sqlManager.updateById(newsLeague);
            return ReturnT.success("编辑成功");
        }else{
            sqlManager.insert(newsLeague);
            return ReturnT.error("保存成功");
        }
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        NewsLeague newsLeague=sqlManager.unique(NewsLeague.class,id);
        if(newsLeague!=null){
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
        NewsLeague newsLeague=sqlManager.unique(NewsLeague.class,id);
        if(newsLeague!=null){
            return ReturnT.success(newsLeague);
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(NewsLeague newsLeague,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            List<NewsLeague> list = sqlManager.query(NewsLeague.class).select();
            return ReturnT.success(list);
    }

}
