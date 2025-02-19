import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description news_editor
 * @author capkin
 * @date 2025-02-19
 */
@Repository
public class NewsEditorDaoImpl implements INewsEditorDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(NewsEditor newsEditor) {
        return jdbcTemplate.update("insert into news_editor  (id ) values (? )",
        newsEditor.getId());
    }

    @Override
    public int update(NewsEditor newsEditor) {
        return jdbcTemplate.update("UPDATE  news_editor  SET "
        +" where id=?",
            
            newsEditor.getId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from news_editor where id=?",id);
    }

    @Override
    public NewsEditor findById(int id) {
        List<NewsEditor> list = jdbcTemplate.query("select * from news_editor where id=?", new Object[]{id}, new BeanPropertyRowMapper<NewsEditor>(NewsEditor.class));
        if(list!=null && !list.isEmpty() ){
            return  list.get(0);
        }else{
             return null;
        }
    }

    @Override
    public List<NewsEditor> findAllList(Map<String,Object> params) {
        List<NewsEditor> list = jdbcTemplate.query("select * from news_editor", new Object[]{}, new BeanPropertyRowMapper<NewsEditor>(NewsEditor.class));
        if(list!=null && !list.isEmpty() ){
            return list;
        }else{
            return Collections.emptyList();
        }
    }

}
