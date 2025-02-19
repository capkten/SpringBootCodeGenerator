import java.util.Map;
/**
 * @description news_team
 * @author capkin
 * @date 2025-02-19
 */
public interface NewsTeamService {

    /**
    * 新增
    */
    public Object insert(NewsTeam newsTeam);

    /**
    * 删除
    */
    public Object delete(int id);

    /**
    * 更新
    */
    public Object update(NewsTeam newsTeam);

    /**
    * 根据主键 id 查询
    */
    public NewsTeam load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
