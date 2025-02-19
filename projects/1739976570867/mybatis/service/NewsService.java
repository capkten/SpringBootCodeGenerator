import java.util.Map;
/**
 * @description news
 * @author capkin
 * @date 2025-02-19
 */
public interface NewsService {

    /**
    * 新增
    */
    public Object insert(News news);

    /**
    * 删除
    */
    public Object delete(int id);

    /**
    * 更新
    */
    public Object update(News news);

    /**
    * 根据主键 id 查询
    */
    public News load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
