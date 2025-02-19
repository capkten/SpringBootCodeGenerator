import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
/**
 * @description posts_team
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/postsTeam")
public class PostsTeamController {

    @Autowired
    private SQLManager sqlManager;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(PostsTeam postsTeam){
        PostsTeam postsTeam=sqlManager.unique(PostsTeam.class,postsTeam.getId());
        if(postsTeam!=null){
            sqlManager.updateById(postsTeam);
            return ReturnT.success("编辑成功");
        }else{
            sqlManager.insert(postsTeam);
            return ReturnT.error("保存成功");
        }
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        PostsTeam postsTeam=sqlManager.unique(PostsTeam.class,id);
        if(postsTeam!=null){
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
        PostsTeam postsTeam=sqlManager.unique(PostsTeam.class,id);
        if(postsTeam!=null){
            return ReturnT.success(postsTeam);
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(PostsTeam postsTeam,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            List<PostsTeam> list = sqlManager.query(PostsTeam.class).select();
            return ReturnT.success(list);
    }

}
