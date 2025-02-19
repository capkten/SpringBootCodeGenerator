import java.util.Map;
/**
 * @description likes_posts
 * @author capkin
 * @date 2025-02-19
 */
public interface LikesPostsService {

    /**
    * 新增
    */
    public Object insert(LikesPosts likesPosts);

    /**
    * 删除
    */
    public Object delete(int id);

    /**
    * 更新
    */
    public Object update(LikesPosts likesPosts);

    /**
    * 根据主键 id 查询
    */
    public LikesPosts load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
