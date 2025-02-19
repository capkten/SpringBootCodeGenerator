import java.util.List;
/**
 * @description report_posts
 * @author capkin
 * @date 2025-02-19
 */
public interface IReportPostsDAO {

    int add(ReportPosts reportPosts);

    int update(ReportPosts reportPosts);

    int delete(int id);

    ReportPosts findById(int id);

    List<ReportPosts> findAllList(Map<String,Object> param);

}
