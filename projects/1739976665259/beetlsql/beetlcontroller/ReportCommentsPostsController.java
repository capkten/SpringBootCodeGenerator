import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
/**
 * @description report_comments_posts
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/reportCommentsPosts")
public class ReportCommentsPostsController {

    @Autowired
    private SQLManager sqlManager;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(ReportCommentsPosts reportCommentsPosts){
        ReportCommentsPosts reportCommentsPosts=sqlManager.unique(ReportCommentsPosts.class,reportCommentsPosts.getId());
        if(reportCommentsPosts!=null){
            sqlManager.updateById(reportCommentsPosts);
            return ReturnT.success("编辑成功");
        }else{
            sqlManager.insert(reportCommentsPosts);
            return ReturnT.error("保存成功");
        }
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        ReportCommentsPosts reportCommentsPosts=sqlManager.unique(ReportCommentsPosts.class,id);
        if(reportCommentsPosts!=null){
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
        ReportCommentsPosts reportCommentsPosts=sqlManager.unique(ReportCommentsPosts.class,id);
        if(reportCommentsPosts!=null){
            return ReturnT.success(reportCommentsPosts);
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(ReportCommentsPosts reportCommentsPosts,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            List<ReportCommentsPosts> list = sqlManager.query(ReportCommentsPosts.class).select();
            return ReturnT.success(list);
    }

}
