import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description likes_news
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public class LikesNewsDaoImpl implements ILikesNewsDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(LikesNews likesNews) {
        return jdbcTemplate.update("insert into likes_news  (id ) values (? )",
        likesNews.getId());
    }

    @Override
    public int update(LikesNews likesNews) {
        return jdbcTemplate.update("UPDATE  likes_news  SET "
        +" where id=?",
            
            likesNews.getId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from likes_news where id=?",id);
    }

    @Override
    public LikesNews findById(int id) {
        List<LikesNews> list = jdbcTemplate.query("select * from likes_news where id=?", new Object[]{id}, new BeanPropertyRowMapper<LikesNews>(LikesNews.class));
        if(list!=null && !list.isEmpty() ){
            return  list.get(0);
        }else{
             return null;
        }
    }

    @Override
    public List<LikesNews> findAllList(Map<String,Object> params) {
        List<LikesNews> list = jdbcTemplate.query("select * from likes_news", new Object[]{}, new BeanPropertyRowMapper<LikesNews>(LikesNews.class));
        if(list!=null && !list.isEmpty() ){
            return list;
        }else{
            return Collections.emptyList();
        }
    }

}
