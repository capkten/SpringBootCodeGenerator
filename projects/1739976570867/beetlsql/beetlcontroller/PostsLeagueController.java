import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
/**
 * @description posts_league
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/postsLeague")
public class PostsLeagueController {

    @Autowired
    private SQLManager sqlManager;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(PostsLeague postsLeague){
        PostsLeague postsLeague=sqlManager.unique(PostsLeague.class,postsLeague.getId());
        if(postsLeague!=null){
            sqlManager.updateById(postsLeague);
            return ReturnT.success("编辑成功");
        }else{
            sqlManager.insert(postsLeague);
            return ReturnT.error("保存成功");
        }
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        PostsLeague postsLeague=sqlManager.unique(PostsLeague.class,id);
        if(postsLeague!=null){
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
        PostsLeague postsLeague=sqlManager.unique(PostsLeague.class,id);
        if(postsLeague!=null){
            return ReturnT.success(postsLeague);
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(PostsLeague postsLeague,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            List<PostsLeague> list = sqlManager.query(PostsLeague.class).select();
            return ReturnT.success(list);
    }

}
