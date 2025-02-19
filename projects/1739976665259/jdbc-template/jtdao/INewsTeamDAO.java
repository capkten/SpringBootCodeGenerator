import java.util.List;
/**
 * @description news_team
 * @author capkin
 * @date 2025-02-19
 */
public interface INewsTeamDAO {

    int add(NewsTeam newsTeam);

    int update(NewsTeam newsTeam);

    int delete(int id);

    NewsTeam findById(int id);

    List<NewsTeam> findAllList(Map<String,Object> param);

}
