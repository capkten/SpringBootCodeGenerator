import java.util.List;
/**
 * @description comments_posts
 * @author capkin
 * @date 2025-02-19
 */
public interface ICommentsPostsDAO {

    int add(CommentsPosts commentsPosts);

    int update(CommentsPosts commentsPosts);

    int delete(int id);

    CommentsPosts findById(int id);

    List<CommentsPosts> findAllList(Map<String,Object> param);

}
