import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description report_comments_news
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public class ReportCommentsNewsDaoImpl implements IReportCommentsNewsDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(ReportCommentsNews reportCommentsNews) {
        return jdbcTemplate.update("insert into report_comments_news  (cn_id ) values (? )",
        reportCommentsNews.getCnId());
    }

    @Override
    public int update(ReportCommentsNews reportCommentsNews) {
        return jdbcTemplate.update("UPDATE  report_comments_news  SET "
        +" where cn_id=?",
            
            reportCommentsNews.getCnId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from report_comments_news where cn_id=?",id);
    }

    @Override
    public ReportCommentsNews findById(int id) {
        List<ReportCommentsNews> list = jdbcTemplate.query("select * from report_comments_news where cn_id=?", new Object[]{id}, new BeanPropertyRowMapper<ReportCommentsNews>(ReportCommentsNews.class));
        if(list!=null && !list.isEmpty() ){
            return  list.get(0);
        }else{
             return null;
        }
    }

    @Override
    public List<ReportCommentsNews> findAllList(Map<String,Object> params) {
        List<ReportCommentsNews> list = jdbcTemplate.query("select * from report_comments_news", new Object[]{}, new BeanPropertyRowMapper<ReportCommentsNews>(ReportCommentsNews.class));
        if(list!=null && !list.isEmpty() ){
            return list;
        }else{
            return Collections.emptyList();
        }
    }

}
