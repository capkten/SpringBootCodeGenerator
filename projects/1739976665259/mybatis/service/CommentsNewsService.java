import java.util.Map;
/**
 * @description comments_news
 * @author capkin
 * @date 2025-02-19
 */
public interface CommentsNewsService {

    /**
    * 新增
    */
    public Object insert(CommentsNews commentsNews);

    /**
    * 删除
    */
    public Object delete(int id);

    /**
    * 更新
    */
    public Object update(CommentsNews commentsNews);

    /**
    * 根据主键 id 查询
    */
    public CommentsNews load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
