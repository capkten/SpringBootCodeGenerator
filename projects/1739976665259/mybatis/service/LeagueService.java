import java.util.Map;
/**
 * @description league
 * @author capkin
 * @date 2025-02-19
 */
public interface LeagueService {

    /**
    * 新增
    */
    public Object insert(League league);

    /**
    * 删除
    */
    public Object delete(int id);

    /**
    * 更新
    */
    public Object update(League league);

    /**
    * 根据主键 id 查询
    */
    public League load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
