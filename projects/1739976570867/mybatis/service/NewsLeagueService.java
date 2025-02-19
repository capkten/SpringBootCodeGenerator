import java.util.Map;
/**
 * @description news_league
 * @author capkin
 * @date 2025-02-19
 */
public interface NewsLeagueService {

    /**
    * 新增
    */
    public Object insert(NewsLeague newsLeague);

    /**
    * 删除
    */
    public Object delete(int id);

    /**
    * 更新
    */
    public Object update(NewsLeague newsLeague);

    /**
    * 根据主键 id 查询
    */
    public NewsLeague load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
