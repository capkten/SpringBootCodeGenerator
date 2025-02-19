import java.util.Map;
/**
 * @description comments_posts
 * @author capkin
 * @date 2025-02-19
 */
public interface CommentsPostsService {

    /**
    * 新增
    */
    public Object insert(CommentsPosts commentsPosts);

    /**
    * 删除
    */
    public Object delete(int id);

    /**
    * 更新
    */
    public Object update(CommentsPosts commentsPosts);

    /**
    * 根据主键 id 查询
    */
    public CommentsPosts load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
