import java.util.Map;
/**
 * @description collect_posts
 * @author capkin
 * @date 2025-02-19
 */
public interface CollectPostsService {

    /**
    * 新增
    */
    public Object insert(CollectPosts collectPosts);

    /**
    * 删除
    */
    public Object delete(int id);

    /**
    * 更新
    */
    public Object update(CollectPosts collectPosts);

    /**
    * 根据主键 id 查询
    */
    public CollectPosts load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
