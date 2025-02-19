import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description collect_posts
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public class CollectPostsDaoImpl implements ICollectPostsDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(CollectPosts collectPosts) {
        return jdbcTemplate.update("insert into collect_posts  (id ) values (? )",
        collectPosts.getId());
    }

    @Override
    public int update(CollectPosts collectPosts) {
        return jdbcTemplate.update("UPDATE  collect_posts  SET "
        +" where id=?",
            
            collectPosts.getId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from collect_posts where id=?",id);
    }

    @Override
    public CollectPosts findById(int id) {
        List<CollectPosts> list = jdbcTemplate.query("select * from collect_posts where id=?", new Object[]{id}, new BeanPropertyRowMapper<CollectPosts>(CollectPosts.class));
        if(list!=null && !list.isEmpty() ){
            return  list.get(0);
        }else{
             return null;
        }
    }

    @Override
    public List<CollectPosts> findAllList(Map<String,Object> params) {
        List<CollectPosts> list = jdbcTemplate.query("select * from collect_posts", new Object[]{}, new BeanPropertyRowMapper<CollectPosts>(CollectPosts.class));
        if(list!=null && !list.isEmpty() ){
            return list;
        }else{
            return Collections.emptyList();
        }
    }

}
