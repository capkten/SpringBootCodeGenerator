import java.util.List;
/**
 * @description news_league
 * @author capkin
 * @date 2025-02-19
 */
public interface INewsLeagueDAO {

    int add(NewsLeague newsLeague);

    int update(NewsLeague newsLeague);

    int delete(int id);

    NewsLeague findById(int id);

    List<NewsLeague> findAllList(Map<String,Object> param);

}
