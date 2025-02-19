import java.util.List;
/**
 * @description collect_news
 * @author capkin
 * @date 2025-02-19
 */
public interface ICollectNewsDAO {

    int add(CollectNews collectNews);

    int update(CollectNews collectNews);

    int delete(int id);

    CollectNews findById(int id);

    List<CollectNews> findAllList(Map<String,Object> param);

}
