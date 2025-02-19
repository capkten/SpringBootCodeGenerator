import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
/**
 * @description collect_news
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/collectNews")
public class CollectNewsController {

    @Autowired
    private SQLManager sqlManager;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(CollectNews collectNews){
        CollectNews collectNews=sqlManager.unique(CollectNews.class,collectNews.getId());
        if(collectNews!=null){
            sqlManager.updateById(collectNews);
            return ReturnT.success("编辑成功");
        }else{
            sqlManager.insert(collectNews);
            return ReturnT.error("保存成功");
        }
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        CollectNews collectNews=sqlManager.unique(CollectNews.class,id);
        if(collectNews!=null){
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
        CollectNews collectNews=sqlManager.unique(CollectNews.class,id);
        if(collectNews!=null){
            return ReturnT.success(collectNews);
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(CollectNews collectNews,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            List<CollectNews> list = sqlManager.query(CollectNews.class).select();
            return ReturnT.success(list);
    }

}
