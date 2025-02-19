import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
/**
 * @description likes_news
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/likesNews")
public class LikesNewsController {

    @Autowired
    private SQLManager sqlManager;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(LikesNews likesNews){
        LikesNews likesNews=sqlManager.unique(LikesNews.class,likesNews.getId());
        if(likesNews!=null){
            sqlManager.updateById(likesNews);
            return ReturnT.success("编辑成功");
        }else{
            sqlManager.insert(likesNews);
            return ReturnT.error("保存成功");
        }
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        LikesNews likesNews=sqlManager.unique(LikesNews.class,id);
        if(likesNews!=null){
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
        LikesNews likesNews=sqlManager.unique(LikesNews.class,id);
        if(likesNews!=null){
            return ReturnT.success(likesNews);
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
            List<LikesNews> list = sqlManager.query(LikesNews.class).select();
            return ReturnT.success(list);
    }

}
