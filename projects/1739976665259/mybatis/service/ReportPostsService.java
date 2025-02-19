import java.util.Map;
/**
 * @description report_posts
 * @author capkin
 * @date 2025-02-19
 */
public interface ReportPostsService {

    /**
    * 新增
    */
    public Object insert(ReportPosts reportPosts);

    /**
    * 删除
    */
    public Object delete(int id);

    /**
    * 更新
    */
    public Object update(ReportPosts reportPosts);

    /**
    * 根据主键 id 查询
    */
    public ReportPosts load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
