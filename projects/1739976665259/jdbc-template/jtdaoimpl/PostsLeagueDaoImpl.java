import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description posts_league
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public class PostsLeagueDaoImpl implements IPostsLeagueDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(PostsLeague postsLeague) {
        return jdbcTemplate.update("insert into posts_league  (id ) values (? )",
        postsLeague.getId());
    }

    @Override
    public int update(PostsLeague postsLeague) {
        return jdbcTemplate.update("UPDATE  posts_league  SET "
        +" where id=?",
            
            postsLeague.getId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from posts_league where id=?",id);
    }

    @Override
    public PostsLeague findById(int id) {
        List<PostsLeague> list = jdbcTemplate.query("select * from posts_league where id=?", new Object[]{id}, new BeanPropertyRowMapper<PostsLeague>(PostsLeague.class));
        if(list!=null && !list.isEmpty() ){
            return  list.get(0);
        }else{
             return null;
        }
    }

    @Override
    public List<PostsLeague> findAllList(Map<String,Object> params) {
        List<PostsLeague> list = jdbcTemplate.query("select * from posts_league", new Object[]{}, new BeanPropertyRowMapper<PostsLeague>(PostsLeague.class));
        if(list!=null && !list.isEmpty() ){
            return list;
        }else{
            return Collections.emptyList();
        }
    }

}
