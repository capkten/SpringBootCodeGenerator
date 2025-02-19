import java.util.List;
/**
 * @description likes_posts
 * @author capkin
 * @date 2025-02-19
 */
public interface ILikesPostsDAO {

    int add(LikesPosts likesPosts);

    int update(LikesPosts likesPosts);

    int delete(int id);

    LikesPosts findById(int id);

    List<LikesPosts> findAllList(Map<String,Object> param);

}
