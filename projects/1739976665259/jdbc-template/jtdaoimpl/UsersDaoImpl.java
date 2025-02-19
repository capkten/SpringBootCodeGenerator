import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description users
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public class UsersDaoImpl implements IUsersDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Users users) {
        return jdbcTemplate.update("insert into users  (id ) values (? )",
        users.getId());
    }

    @Override
    public int update(Users users) {
        return jdbcTemplate.update("UPDATE  users  SET "
        +" where id=?",
            
            users.getId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from users where id=?",id);
    }

    @Override
    public Users findById(int id) {
        List<Users> list = jdbcTemplate.query("select * from users where id=?", new Object[]{id}, new BeanPropertyRowMapper<Users>(Users.class));
        if(list!=null && !list.isEmpty() ){
            return  list.get(0);
        }else{
             return null;
        }
    }

    @Override
    public List<Users> findAllList(Map<String,Object> params) {
        List<Users> list = jdbcTemplate.query("select * from users", new Object[]{}, new BeanPropertyRowMapper<Users>(Users.class));
        if(list!=null && !list.isEmpty() ){
            return list;
        }else{
            return Collections.emptyList();
        }
    }

}
