import java.util.List;
/**
 * @description news
 * @author capkin
 * @date 2025-02-19
 */
public interface INewsDAO {

    int add(News news);

    int update(News news);

    int delete(int id);

    News findById(int id);

    List<News> findAllList(Map<String,Object> param);

}
