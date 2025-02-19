import java.util.Map;
/**
 * @description collect_news
 * @author capkin
 * @date 2025-02-19
 */
public interface CollectNewsService {

    /**
    * 新增
    */
    public Object insert(CollectNews collectNews);

    /**
    * 删除
    */
    public Object delete(int id);

    /**
    * 更新
    */
    public Object update(CollectNews collectNews);

    /**
    * 根据主键 id 查询
    */
    public CollectNews load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
