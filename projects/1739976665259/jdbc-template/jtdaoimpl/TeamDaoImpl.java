import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description team
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public class TeamDaoImpl implements ITeamDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Team team) {
        return jdbcTemplate.update("insert into team  (id ) values (? )",
        team.getId());
    }

    @Override
    public int update(Team team) {
        return jdbcTemplate.update("UPDATE  team  SET "
        +" where id=?",
            
            team.getId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from team where id=?",id);
    }

    @Override
    public Team findById(int id) {
        List<Team> list = jdbcTemplate.query("select * from team where id=?", new Object[]{id}, new BeanPropertyRowMapper<Team>(Team.class));
        if(list!=null && !list.isEmpty() ){
            return  list.get(0);
        }else{
             return null;
        }
    }

    @Override
    public List<Team> findAllList(Map<String,Object> params) {
        List<Team> list = jdbcTemplate.query("select * from team", new Object[]{}, new BeanPropertyRowMapper<Team>(Team.class));
        if(list!=null && !list.isEmpty() ){
            return list;
        }else{
            return Collections.emptyList();
        }
    }

}
