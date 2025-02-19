import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description news_league
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public class NewsLeagueDaoImpl implements INewsLeagueDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(NewsLeague newsLeague) {
        return jdbcTemplate.update("insert into news_league  (n_id ) values (? )",
        newsLeague.getNId());
    }

    @Override
    public int update(NewsLeague newsLeague) {
        return jdbcTemplate.update("UPDATE  news_league  SET "
        +" where n_id=?",
            
            newsLeague.getNId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from news_league where n_id=?",id);
    }

    @Override
    public NewsLeague findById(int id) {
        List<NewsLeague> list = jdbcTemplate.query("select * from news_league where n_id=?", new Object[]{id}, new BeanPropertyRowMapper<NewsLeague>(NewsLeague.class));
        if(list!=null && !list.isEmpty() ){
            return  list.get(0);
        }else{
             return null;
        }
    }

    @Override
    public List<NewsLeague> findAllList(Map<String,Object> params) {
        List<NewsLeague> list = jdbcTemplate.query("select * from news_league", new Object[]{}, new BeanPropertyRowMapper<NewsLeague>(NewsLeague.class));
        if(list!=null && !list.isEmpty() ){
            return list;
        }else{
            return Collections.emptyList();
        }
    }

}
