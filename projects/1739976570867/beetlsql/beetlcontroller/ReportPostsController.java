import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
/**
 * @description report_posts
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/reportPosts")
public class ReportPostsController {

    @Autowired
    private SQLManager sqlManager;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(ReportPosts reportPosts){
        ReportPosts reportPosts=sqlManager.unique(ReportPosts.class,reportPosts.getId());
        if(reportPosts!=null){
            sqlManager.updateById(reportPosts);
            return ReturnT.success("编辑成功");
        }else{
            sqlManager.insert(reportPosts);
            return ReturnT.error("保存成功");
        }
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        ReportPosts reportPosts=sqlManager.unique(ReportPosts.class,id);
        if(reportPosts!=null){
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
        ReportPosts reportPosts=sqlManager.unique(ReportPosts.class,id);
        if(reportPosts!=null){
            return ReturnT.success(reportPosts);
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(ReportPosts reportPosts,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            List<ReportPosts> list = sqlManager.query(ReportPosts.class).select();
            return ReturnT.success(list);
    }

}
