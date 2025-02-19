import java.util.List;
/**
 * @description users
 * @author capkin
 * @date 2025-02-19
 */
public interface IUsersDAO {

    int add(Users users);

    int update(Users users);

    int delete(int id);

    Users findById(int id);

    List<Users> findAllList(Map<String,Object> param);

}
