import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
/**
 * @description comments_news
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/commentsNews")
public class CommentsNewsController {

    @Autowired
    private SQLManager sqlManager;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(CommentsNews commentsNews){
        CommentsNews commentsNews=sqlManager.unique(CommentsNews.class,commentsNews.getId());
        if(commentsNews!=null){
            sqlManager.updateById(commentsNews);
            return ReturnT.success("编辑成功");
        }else{
            sqlManager.insert(commentsNews);
            return ReturnT.error("保存成功");
        }
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        CommentsNews commentsNews=sqlManager.unique(CommentsNews.class,id);
        if(commentsNews!=null){
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
        CommentsNews commentsNews=sqlManager.unique(CommentsNews.class,id);
        if(commentsNews!=null){
            return ReturnT.success(commentsNews);
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(CommentsNews commentsNews,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            List<CommentsNews> list = sqlManager.query(CommentsNews.class).select();
            return ReturnT.success(list);
    }

}
