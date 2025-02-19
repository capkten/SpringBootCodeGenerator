import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description collect_news
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public class CollectNewsDaoImpl implements ICollectNewsDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(CollectNews collectNews) {
        return jdbcTemplate.update("insert into collect_news  (id ) values (? )",
        collectNews.getId());
    }

    @Override
    public int update(CollectNews collectNews) {
        return jdbcTemplate.update("UPDATE  collect_news  SET "
        +" where id=?",
            
            collectNews.getId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from collect_news where id=?",id);
    }

    @Override
    public CollectNews findById(int id) {
        List<CollectNews> list = jdbcTemplate.query("select * from collect_news where id=?", new Object[]{id}, new BeanPropertyRowMapper<CollectNews>(CollectNews.class));
        if(list!=null && !list.isEmpty() ){
            return  list.get(0);
        }else{
             return null;
        }
    }

    @Override
    public List<CollectNews> findAllList(Map<String,Object> params) {
        List<CollectNews> list = jdbcTemplate.query("select * from collect_news", new Object[]{}, new BeanPropertyRowMapper<CollectNews>(CollectNews.class));
        if(list!=null && !list.isEmpty() ){
            return list;
        }else{
            return Collections.emptyList();
        }
    }

}
