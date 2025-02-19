import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description admin
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public class AdminDaoImpl implements IAdminDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Admin admin) {
        return jdbcTemplate.update("insert into admin  (id ) values (? )",
        admin.getId());
    }

    @Override
    public int update(Admin admin) {
        return jdbcTemplate.update("UPDATE  admin  SET "
        +" where id=?",
            
            admin.getId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from admin where id=?",id);
    }

    @Override
    public Admin findById(int id) {
        List<Admin> list = jdbcTemplate.query("select * from admin where id=?", new Object[]{id}, new BeanPropertyRowMapper<Admin>(Admin.class));
        if(list!=null && !list.isEmpty() ){
            return  list.get(0);
        }else{
             return null;
        }
    }

    @Override
    public List<Admin> findAllList(Map<String,Object> params) {
        List<Admin> list = jdbcTemplate.query("select * from admin", new Object[]{}, new BeanPropertyRowMapper<Admin>(Admin.class));
        if(list!=null && !list.isEmpty() ){
            return list;
        }else{
            return Collections.emptyList();
        }
    }

}
