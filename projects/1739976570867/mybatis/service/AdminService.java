import java.util.Map;
/**
 * @description admin
 * @author capkin
 * @date 2025-02-19
 */
public interface AdminService {

    /**
    * 新增
    */
    public Object insert(Admin admin);

    /**
    * 删除
    */
    public Object delete(int id);

    /**
    * 更新
    */
    public Object update(Admin admin);

    /**
    * 根据主键 id 查询
    */
    public Admin load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
