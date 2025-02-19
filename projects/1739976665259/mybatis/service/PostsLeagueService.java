import java.util.Map;
/**
 * @description posts_league
 * @author capkin
 * @date 2025-02-19
 */
public interface PostsLeagueService {

    /**
    * 新增
    */
    public Object insert(PostsLeague postsLeague);

    /**
    * 删除
    */
    public Object delete(int id);

    /**
    * 更新
    */
    public Object update(PostsLeague postsLeague);

    /**
    * 根据主键 id 查询
    */
    public PostsLeague load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
