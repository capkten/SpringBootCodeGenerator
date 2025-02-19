import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
/**
 * @description report_comments_news
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/reportCommentsNews")
public class ReportCommentsNewsController {

    @Autowired
    private SQLManager sqlManager;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(ReportCommentsNews reportCommentsNews){
        ReportCommentsNews reportCommentsNews=sqlManager.unique(ReportCommentsNews.class,reportCommentsNews.getId());
        if(reportCommentsNews!=null){
            sqlManager.updateById(reportCommentsNews);
            return ReturnT.success("编辑成功");
        }else{
            sqlManager.insert(reportCommentsNews);
            return ReturnT.error("保存成功");
        }
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        ReportCommentsNews reportCommentsNews=sqlManager.unique(ReportCommentsNews.class,id);
        if(reportCommentsNews!=null){
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
        ReportCommentsNews reportCommentsNews=sqlManager.unique(ReportCommentsNews.class,id);
        if(reportCommentsNews!=null){
            return ReturnT.success(reportCommentsNews);
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(ReportCommentsNews reportCommentsNews,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            List<ReportCommentsNews> list = sqlManager.query(ReportCommentsNews.class).select();
            return ReturnT.success(list);
    }

}
