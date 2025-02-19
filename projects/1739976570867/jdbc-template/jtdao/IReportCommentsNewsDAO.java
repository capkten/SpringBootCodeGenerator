import java.util.List;
/**
 * @description report_comments_news
 * @author capkin
 * @date 2025-02-19
 */
public interface IReportCommentsNewsDAO {

    int add(ReportCommentsNews reportCommentsNews);

    int update(ReportCommentsNews reportCommentsNews);

    int delete(int id);

    ReportCommentsNews findById(int id);

    List<ReportCommentsNews> findAllList(Map<String,Object> param);

}
