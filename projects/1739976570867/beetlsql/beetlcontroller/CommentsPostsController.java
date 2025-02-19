import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
/**
 * @description comments_posts
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/commentsPosts")
public class CommentsPostsController {

    @Autowired
    private SQLManager sqlManager;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(CommentsPosts commentsPosts){
        CommentsPosts commentsPosts=sqlManager.unique(CommentsPosts.class,commentsPosts.getId());
        if(commentsPosts!=null){
            sqlManager.updateById(commentsPosts);
            return ReturnT.success("编辑成功");
        }else{
            sqlManager.insert(commentsPosts);
            return ReturnT.error("保存成功");
        }
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        CommentsPosts commentsPosts=sqlManager.unique(CommentsPosts.class,id);
        if(commentsPosts!=null){
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
        CommentsPosts commentsPosts=sqlManager.unique(CommentsPosts.class,id);
        if(commentsPosts!=null){
            return ReturnT.success(commentsPosts);
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(CommentsPosts commentsPosts,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            List<CommentsPosts> list = sqlManager.query(CommentsPosts.class).select();
            return ReturnT.success(list);
    }

}
