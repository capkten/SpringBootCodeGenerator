import java.util.List;
/**
 * @description league
 * @author capkin
 * @date 2025-02-19
 */
public interface ILeagueDAO {

    int add(League league);

    int update(League league);

    int delete(int id);

    League findById(int id);

    List<League> findAllList(Map<String,Object> param);

}
