import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
/**
 * @description posts
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private SQLManager sqlManager;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(Posts posts){
        Posts posts=sqlManager.unique(Posts.class,posts.getId());
        if(posts!=null){
            sqlManager.updateById(posts);
            return ReturnT.success("编辑成功");
        }else{
            sqlManager.insert(posts);
            return ReturnT.error("保存成功");
        }
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        Posts posts=sqlManager.unique(Posts.class,id);
        if(posts!=null){
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
        Posts posts=sqlManager.unique(Posts.class,id);
        if(posts!=null){
            return ReturnT.success(posts);
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(Posts posts,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            List<Posts> list = sqlManager.query(Posts.class).select();
            return ReturnT.success(list);
    }

}
