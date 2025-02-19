import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description league
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public class LeagueDaoImpl implements ILeagueDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(League league) {
        return jdbcTemplate.update("insert into league  (id ) values (? )",
        league.getId());
    }

    @Override
    public int update(League league) {
        return jdbcTemplate.update("UPDATE  league  SET "
        +" where id=?",
            
            league.getId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from league where id=?",id);
    }

    @Override
    public League findById(int id) {
        List<League> list = jdbcTemplate.query("select * from league where id=?", new Object[]{id}, new BeanPropertyRowMapper<League>(League.class));
        if(list!=null && !list.isEmpty() ){
            return  list.get(0);
        }else{
             return null;
        }
    }

    @Override
    public List<League> findAllList(Map<String,Object> params) {
        List<League> list = jdbcTemplate.query("select * from league", new Object[]{}, new BeanPropertyRowMapper<League>(League.class));
        if(list!=null && !list.isEmpty() ){
            return list;
        }else{
            return Collections.emptyList();
        }
    }

}
