import java.util.List;
/**
 * @description posts_league
 * @author capkin
 * @date 2025-02-19
 */
public interface IPostsLeagueDAO {

    int add(PostsLeague postsLeague);

    int update(PostsLeague postsLeague);

    int delete(int id);

    PostsLeague findById(int id);

    List<PostsLeague> findAllList(Map<String,Object> param);

}
