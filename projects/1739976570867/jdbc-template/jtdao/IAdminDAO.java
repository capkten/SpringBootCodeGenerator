import java.util.List;
/**
 * @description admin
 * @author capkin
 * @date 2025-02-19
 */
public interface IAdminDAO {

    int add(Admin admin);

    int update(Admin admin);

    int delete(int id);

    Admin findById(int id);

    List<Admin> findAllList(Map<String,Object> param);

}
