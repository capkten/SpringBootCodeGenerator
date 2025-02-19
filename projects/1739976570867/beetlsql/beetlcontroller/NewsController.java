import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
/**
 * @description news
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private SQLManager sqlManager;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(News news){
        News news=sqlManager.unique(News.class,news.getId());
        if(news!=null){
            sqlManager.updateById(news);
            return ReturnT.success("编辑成功");
        }else{
            sqlManager.insert(news);
            return ReturnT.error("保存成功");
        }
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        News news=sqlManager.unique(News.class,id);
        if(news!=null){
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
        News news=sqlManager.unique(News.class,id);
        if(news!=null){
            return ReturnT.success(news);
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(News news,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            List<News> list = sqlManager.query(News.class).select();
            return ReturnT.success(list);
    }

}
