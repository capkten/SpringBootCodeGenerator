import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description posts_team
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public class PostsTeamDaoImpl implements IPostsTeamDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(PostsTeam postsTeam) {
        return jdbcTemplate.update("insert into posts_team  (id ) values (? )",
        postsTeam.getId());
    }

    @Override
    public int update(PostsTeam postsTeam) {
        return jdbcTemplate.update("UPDATE  posts_team  SET "
        +" where id=?",
            
            postsTeam.getId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from posts_team where id=?",id);
    }

    @Override
    public PostsTeam findById(int id) {
        List<PostsTeam> list = jdbcTemplate.query("select * from posts_team where id=?", new Object[]{id}, new BeanPropertyRowMapper<PostsTeam>(PostsTeam.class));
        if(list!=null && !list.isEmpty() ){
            return  list.get(0);
        }else{
             return null;
        }
    }

    @Override
    public List<PostsTeam> findAllList(Map<String,Object> params) {
        List<PostsTeam> list = jdbcTemplate.query("select * from posts_team", new Object[]{}, new BeanPropertyRowMapper<PostsTeam>(PostsTeam.class));
        if(list!=null && !list.isEmpty() ){
            return list;
        }else{
            return Collections.emptyList();
        }
    }

}
