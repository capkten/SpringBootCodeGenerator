import java.util.Map;
/**
 * @description team
 * @author capkin
 * @date 2025-02-19
 */
public interface TeamService {

    /**
    * 新增
    */
    public Object insert(Team team);

    /**
    * 删除
    */
    public Object delete(int id);

    /**
    * 更新
    */
    public Object update(Team team);

    /**
    * 根据主键 id 查询
    */
    public Team load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
