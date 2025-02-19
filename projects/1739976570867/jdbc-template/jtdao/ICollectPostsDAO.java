import java.util.List;
/**
 * @description collect_posts
 * @author capkin
 * @date 2025-02-19
 */
public interface ICollectPostsDAO {

    int add(CollectPosts collectPosts);

    int update(CollectPosts collectPosts);

    int delete(int id);

    CollectPosts findById(int id);

    List<CollectPosts> findAllList(Map<String,Object> param);

}
