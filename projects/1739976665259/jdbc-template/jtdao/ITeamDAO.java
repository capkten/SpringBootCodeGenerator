import java.util.List;
/**
 * @description team
 * @author capkin
 * @date 2025-02-19
 */
public interface ITeamDAO {

    int add(Team team);

    int update(Team team);

    int delete(int id);

    Team findById(int id);

    List<Team> findAllList(Map<String,Object> param);

}
