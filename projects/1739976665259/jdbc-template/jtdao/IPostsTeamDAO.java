import java.util.List;
/**
 * @description posts_team
 * @author capkin
 * @date 2025-02-19
 */
public interface IPostsTeamDAO {

    int add(PostsTeam postsTeam);

    int update(PostsTeam postsTeam);

    int delete(int id);

    PostsTeam findById(int id);

    List<PostsTeam> findAllList(Map<String,Object> param);

}
