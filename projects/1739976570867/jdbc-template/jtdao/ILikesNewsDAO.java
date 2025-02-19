import java.util.List;
/**
 * @description likes_news
 * @author capkin
 * @date 2025-02-19
 */
public interface ILikesNewsDAO {

    int add(LikesNews likesNews);

    int update(LikesNews likesNews);

    int delete(int id);

    LikesNews findById(int id);

    List<LikesNews> findAllList(Map<String,Object> param);

}
