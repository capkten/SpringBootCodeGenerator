import java.util.Map;
/**
 * @description users
 * @author capkin
 * @date 2025-02-19
 */
public interface UsersService {

    /**
    * 新增
    */
    public Object insert(Users users);

    /**
    * 删除
    */
    public Object delete(int id);

    /**
    * 更新
    */
    public Object update(Users users);

    /**
    * 根据主键 id 查询
    */
    public Users load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
