import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description news
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public class NewsDaoImpl implements INewsDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(News news) {
        return jdbcTemplate.update("insert into news  (id ) values (? )",
        news.getId());
    }

    @Override
    public int update(News news) {
        return jdbcTemplate.update("UPDATE  news  SET "
        +" where id=?",
            
            news.getId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from news where id=?",id);
    }

    @Override
    public News findById(int id) {
        List<News> list = jdbcTemplate.query("select * from news where id=?", new Object[]{id}, new BeanPropertyRowMapper<News>(News.class));
        if(list!=null && !list.isEmpty() ){
            return  list.get(0);
        }else{
             return null;
        }
    }

    @Override
    public List<News> findAllList(Map<String,Object> params) {
        List<News> list = jdbcTemplate.query("select * from news", new Object[]{}, new BeanPropertyRowMapper<News>(News.class));
        if(list!=null && !list.isEmpty() ){
            return list;
        }else{
            return Collections.emptyList();
        }
    }

}
