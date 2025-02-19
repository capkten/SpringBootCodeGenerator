import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description report_comments_posts
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public class ReportCommentsPostsDaoImpl implements IReportCommentsPostsDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(ReportCommentsPosts reportCommentsPosts) {
        return jdbcTemplate.update("insert into report_comments_posts  (cp_id ) values (? )",
        reportCommentsPosts.getCpId());
    }

    @Override
    public int update(ReportCommentsPosts reportCommentsPosts) {
        return jdbcTemplate.update("UPDATE  report_comments_posts  SET "
        +" where cp_id=?",
            
            reportCommentsPosts.getCpId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from report_comments_posts where cp_id=?",id);
    }

    @Override
    public ReportCommentsPosts findById(int id) {
        List<ReportCommentsPosts> list = jdbcTemplate.query("select * from report_comments_posts where cp_id=?", new Object[]{id}, new BeanPropertyRowMapper<ReportCommentsPosts>(ReportCommentsPosts.class));
        if(list!=null && !list.isEmpty() ){
            return  list.get(0);
        }else{
             return null;
        }
    }

    @Override
    public List<ReportCommentsPosts> findAllList(Map<String,Object> params) {
        List<ReportCommentsPosts> list = jdbcTemplate.query("select * from report_comments_posts", new Object[]{}, new BeanPropertyRowMapper<ReportCommentsPosts>(ReportCommentsPosts.class));
        if(list!=null && !list.isEmpty() ){
            return list;
        }else{
            return Collections.emptyList();
        }
    }

}
