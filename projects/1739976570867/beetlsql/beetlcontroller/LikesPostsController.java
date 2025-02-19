import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
/**
 * @description likes_posts
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/likesPosts")
public class LikesPostsController {

    @Autowired
    private SQLManager sqlManager;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(LikesPosts likesPosts){
        LikesPosts likesPosts=sqlManager.unique(LikesPosts.class,likesPosts.getId());
        if(likesPosts!=null){
            sqlManager.updateById(likesPosts);
            return ReturnT.success("编辑成功");
        }else{
            sqlManager.insert(likesPosts);
            return ReturnT.error("保存成功");
        }
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        LikesPosts likesPosts=sqlManager.unique(LikesPosts.class,id);
        if(likesPosts!=null){
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
        LikesPosts likesPosts=sqlManager.unique(LikesPosts.class,id);
        if(likesPosts!=null){
            return ReturnT.success(likesPosts);
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(LikesPosts likesPosts,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            List<LikesPosts> list = sqlManager.query(LikesPosts.class).select();
            return ReturnT.success(list);
    }

}
