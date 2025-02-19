import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description likes_posts
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public class LikesPostsDaoImpl implements ILikesPostsDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(LikesPosts likesPosts) {
        return jdbcTemplate.update("insert into likes_posts  (id ) values (? )",
        likesPosts.getId());
    }

    @Override
    public int update(LikesPosts likesPosts) {
        return jdbcTemplate.update("UPDATE  likes_posts  SET "
        +" where id=?",
            
            likesPosts.getId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from likes_posts where id=?",id);
    }

    @Override
    public LikesPosts findById(int id) {
        List<LikesPosts> list = jdbcTemplate.query("select * from likes_posts where id=?", new Object[]{id}, new BeanPropertyRowMapper<LikesPosts>(LikesPosts.class));
        if(list!=null && !list.isEmpty() ){
            return  list.get(0);
        }else{
             return null;
        }
    }

    @Override
    public List<LikesPosts> findAllList(Map<String,Object> params) {
        List<LikesPosts> list = jdbcTemplate.query("select * from likes_posts", new Object[]{}, new BeanPropertyRowMapper<LikesPosts>(LikesPosts.class));
        if(list!=null && !list.isEmpty() ){
            return list;
        }else{
            return Collections.emptyList();
        }
    }

}
