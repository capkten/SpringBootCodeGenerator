import java.util.Map;
/**
 * @description report_comments_posts
 * @author capkin
 * @date 2025-02-19
 */
public interface ReportCommentsPostsService {

    /**
    * 新增
    */
    public Object insert(ReportCommentsPosts reportCommentsPosts);

    /**
    * 删除
    */
    public Object delete(int id);

    /**
    * 更新
    */
    public Object update(ReportCommentsPosts reportCommentsPosts);

    /**
    * 根据主键 id 查询
    */
    public ReportCommentsPosts load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
