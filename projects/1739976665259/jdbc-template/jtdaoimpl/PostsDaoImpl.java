import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description posts
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public class PostsDaoImpl implements IPostsDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Posts posts) {
        return jdbcTemplate.update("insert into posts  (id ) values (? )",
        posts.getId());
    }

    @Override
    public int update(Posts posts) {
        return jdbcTemplate.update("UPDATE  posts  SET "
        +" where id=?",
            
            posts.getId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from posts where id=?",id);
    }

    @Override
    public Posts findById(int id) {
        List<Posts> list = jdbcTemplate.query("select * from posts where id=?", new Object[]{id}, new BeanPropertyRowMapper<Posts>(Posts.class));
        if(list!=null && !list.isEmpty() ){
            return  list.get(0);
        }else{
             return null;
        }
    }

    @Override
    public List<Posts> findAllList(Map<String,Object> params) {
        List<Posts> list = jdbcTemplate.query("select * from posts", new Object[]{}, new BeanPropertyRowMapper<Posts>(Posts.class));
        if(list!=null && !list.isEmpty() ){
            return list;
        }else{
            return Collections.emptyList();
        }
    }

}
