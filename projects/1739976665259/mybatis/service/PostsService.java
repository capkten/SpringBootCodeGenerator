import java.util.Map;
/**
 * @description posts
 * @author capkin
 * @date 2025-02-19
 */
public interface PostsService {

    /**
    * 新增
    */
    public Object insert(Posts posts);

    /**
    * 删除
    */
    public Object delete(int id);

    /**
    * 更新
    */
    public Object update(Posts posts);

    /**
    * 根据主键 id 查询
    */
    public Posts load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
