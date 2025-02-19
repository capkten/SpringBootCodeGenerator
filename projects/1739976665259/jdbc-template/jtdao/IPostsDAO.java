import java.util.List;
/**
 * @description posts
 * @author capkin
 * @date 2025-02-19
 */
public interface IPostsDAO {

    int add(Posts posts);

    int update(Posts posts);

    int delete(int id);

    Posts findById(int id);

    List<Posts> findAllList(Map<String,Object> param);

}
