import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @description report_comments_posts
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping(value = "/reportCommentsPosts")
public class ReportCommentsPostsController {

    @Resource
    private ReportCommentsPostsService reportCommentsPostsService;

    /**
    * 新增
    * @author capkin
    * @date 2025/02/19
    **/
    @RequestMapping("/insert")
    public Object insert(ReportCommentsPosts reportCommentsPosts){
        return reportCommentsPostsService.insert(reportCommentsPosts);
    }

    /**
    * 刪除
    * @author capkin
    * @date 2025/02/19
    **/
    @RequestMapping("/delete")
    public Object delete(int id){
        return reportCommentsPostsService.delete(id);
    }

    /**
    * 更新
    * @author capkin
    * @date 2025/02/19
    **/
    @RequestMapping("/update")
    public Object update(ReportCommentsPosts reportCommentsPosts){
        return reportCommentsPostsService.update(reportCommentsPosts);
    }

    /**
    * 查询 根据主键 id 查询
    * @author capkin
    * @date 2025/02/19
    **/
    @RequestMapping("/load")
    public Object load(int id){
        return reportCommentsPostsService.load(id);
    }

    /**
    * 查询 分页查询
    * @author capkin
    * @date 2025/02/19
    **/
    @RequestMapping("/pageList")
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                                        @RequestParam(required = false, defaultValue = "10") int pagesize) {
        return reportCommentsPostsService.pageList(offset, pagesize);
    }

}
