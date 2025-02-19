import java.util.Map;
/**
 * @description posts_team
 * @author capkin
 * @date 2025-02-19
 */
public interface PostsTeamService {

    /**
    * 新增
    */
    public Object insert(PostsTeam postsTeam);

    /**
    * 删除
    */
    public Object delete(int id);

    /**
    * 更新
    */
    public Object update(PostsTeam postsTeam);

    /**
    * 根据主键 id 查询
    */
    public PostsTeam load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
