import java.util.Map;
/**
 * @description report_comments_news
 * @author capkin
 * @date 2025-02-19
 */
public interface ReportCommentsNewsService {

    /**
    * 新增
    */
    public Object insert(ReportCommentsNews reportCommentsNews);

    /**
    * 删除
    */
    public Object delete(int id);

    /**
    * 更新
    */
    public Object update(ReportCommentsNews reportCommentsNews);

    /**
    * 根据主键 id 查询
    */
    public ReportCommentsNews load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
