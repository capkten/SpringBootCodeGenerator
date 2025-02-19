import java.util.List;
/**
 * @description report_comments_posts
 * @author capkin
 * @date 2025-02-19
 */
public interface IReportCommentsPostsDAO {

    int add(ReportCommentsPosts reportCommentsPosts);

    int update(ReportCommentsPosts reportCommentsPosts);

    int delete(int id);

    ReportCommentsPosts findById(int id);

    List<ReportCommentsPosts> findAllList(Map<String,Object> param);

}
