import java.util.List;
/**
 * @description comments_news
 * @author capkin
 * @date 2025-02-19
 */
public interface ICommentsNewsDAO {

    int add(CommentsNews commentsNews);

    int update(CommentsNews commentsNews);

    int delete(int id);

    CommentsNews findById(int id);

    List<CommentsNews> findAllList(Map<String,Object> param);

}
