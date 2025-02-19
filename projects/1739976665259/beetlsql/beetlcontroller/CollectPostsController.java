import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
/**
 * @description collect_posts
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/collectPosts")
public class CollectPostsController {

    @Autowired
    private SQLManager sqlManager;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(CollectPosts collectPosts){
        CollectPosts collectPosts=sqlManager.unique(CollectPosts.class,collectPosts.getId());
        if(collectPosts!=null){
            sqlManager.updateById(collectPosts);
            return ReturnT.success("编辑成功");
        }else{
            sqlManager.insert(collectPosts);
            return ReturnT.error("保存成功");
        }
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        CollectPosts collectPosts=sqlManager.unique(CollectPosts.class,id);
        if(collectPosts!=null){
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
        CollectPosts collectPosts=sqlManager.unique(CollectPosts.class,id);
        if(collectPosts!=null){
            return ReturnT.success(collectPosts);
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(CollectPosts collectPosts,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            List<CollectPosts> list = sqlManager.query(CollectPosts.class).select();
            return ReturnT.success(list);
    }

}
