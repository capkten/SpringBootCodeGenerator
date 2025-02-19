import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description comments_posts
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public class CommentsPostsDaoImpl implements ICommentsPostsDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(CommentsPosts commentsPosts) {
        return jdbcTemplate.update("insert into comments_posts  (id ) values (? )",
        commentsPosts.getId());
    }

    @Override
    public int update(CommentsPosts commentsPosts) {
        return jdbcTemplate.update("UPDATE  comments_posts  SET "
        +" where id=?",
            
            commentsPosts.getId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from comments_posts where id=?",id);
    }

    @Override
    public CommentsPosts findById(int id) {
        List<CommentsPosts> list = jdbcTemplate.query("select * from comments_posts where id=?", new Object[]{id}, new BeanPropertyRowMapper<CommentsPosts>(CommentsPosts.class));
        if(list!=null && !list.isEmpty() ){
            return  list.get(0);
        }else{
             return null;
        }
    }

    @Override
    public List<CommentsPosts> findAllList(Map<String,Object> params) {
        List<CommentsPosts> list = jdbcTemplate.query("select * from comments_posts", new Object[]{}, new BeanPropertyRowMapper<CommentsPosts>(CommentsPosts.class));
        if(list!=null && !list.isEmpty() ){
            return list;
        }else{
            return Collections.emptyList();
        }
    }

}
