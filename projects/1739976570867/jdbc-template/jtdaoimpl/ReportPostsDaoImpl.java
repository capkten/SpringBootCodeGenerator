import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description report_posts
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public class ReportPostsDaoImpl implements IReportPostsDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(ReportPosts reportPosts) {
        return jdbcTemplate.update("insert into report_posts  (p_id ) values (? )",
        reportPosts.getPId());
    }

    @Override
    public int update(ReportPosts reportPosts) {
        return jdbcTemplate.update("UPDATE  report_posts  SET "
        +" where p_id=?",
            
            reportPosts.getPId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from report_posts where p_id=?",id);
    }

    @Override
    public ReportPosts findById(int id) {
        List<ReportPosts> list = jdbcTemplate.query("select * from report_posts where p_id=?", new Object[]{id}, new BeanPropertyRowMapper<ReportPosts>(ReportPosts.class));
        if(list!=null && !list.isEmpty() ){
            return  list.get(0);
        }else{
             return null;
        }
    }

    @Override
    public List<ReportPosts> findAllList(Map<String,Object> params) {
        List<ReportPosts> list = jdbcTemplate.query("select * from report_posts", new Object[]{}, new BeanPropertyRowMapper<ReportPosts>(ReportPosts.class));
        if(list!=null && !list.isEmpty() ){
            return list;
        }else{
            return Collections.emptyList();
        }
    }

}
