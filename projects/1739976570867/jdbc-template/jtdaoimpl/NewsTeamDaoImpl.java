import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description news_team
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public class NewsTeamDaoImpl implements INewsTeamDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(NewsTeam newsTeam) {
        return jdbcTemplate.update("insert into news_team  (n_id ) values (? )",
        newsTeam.getNId());
    }

    @Override
    public int update(NewsTeam newsTeam) {
        return jdbcTemplate.update("UPDATE  news_team  SET "
        +" where n_id=?",
            
            newsTeam.getNId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from news_team where n_id=?",id);
    }

    @Override
    public NewsTeam findById(int id) {
        List<NewsTeam> list = jdbcTemplate.query("select * from news_team where n_id=?", new Object[]{id}, new BeanPropertyRowMapper<NewsTeam>(NewsTeam.class));
        if(list!=null && !list.isEmpty() ){
            return  list.get(0);
        }else{
             return null;
        }
    }

    @Override
    public List<NewsTeam> findAllList(Map<String,Object> params) {
        List<NewsTeam> list = jdbcTemplate.query("select * from news_team", new Object[]{}, new BeanPropertyRowMapper<NewsTeam>(NewsTeam.class));
        if(list!=null && !list.isEmpty() ){
            return list;
        }else{
            return Collections.emptyList();
        }
    }

}
