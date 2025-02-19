import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description comments_news
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public class CommentsNewsDaoImpl implements ICommentsNewsDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(CommentsNews commentsNews) {
        return jdbcTemplate.update("insert into comments_news  (id ) values (? )",
        commentsNews.getId());
    }

    @Override
    public int update(CommentsNews commentsNews) {
        return jdbcTemplate.update("UPDATE  comments_news  SET "
        +" where id=?",
            
            commentsNews.getId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from comments_news where id=?",id);
    }

    @Override
    public CommentsNews findById(int id) {
        List<CommentsNews> list = jdbcTemplate.query("select * from comments_news where id=?", new Object[]{id}, new BeanPropertyRowMapper<CommentsNews>(CommentsNews.class));
        if(list!=null && !list.isEmpty() ){
            return  list.get(0);
        }else{
             return null;
        }
    }

    @Override
    public List<CommentsNews> findAllList(Map<String,Object> params) {
        List<CommentsNews> list = jdbcTemplate.query("select * from comments_news", new Object[]{}, new BeanPropertyRowMapper<CommentsNews>(CommentsNews.class));
        if(list!=null && !list.isEmpty() ){
            return list;
        }else{
            return Collections.emptyList();
        }
    }

}
