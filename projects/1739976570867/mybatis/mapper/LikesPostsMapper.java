import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description likes_posts
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface LikesPostsMapper {

    /**
    * 新增
    * @author capkin
    * @date 2025/02/19
    **/
    int insert(LikesPosts likesPosts);

    /**
    * 刪除
    * @author capkin
    * @date 2025/02/19
    **/
    int delete(int id);

    /**
    * 更新
    * @author capkin
    * @date 2025/02/19
    **/
    int update(LikesPosts likesPosts);

    /**
    * 查询 根据主键 id 查询
    * @author capkin
    * @date 2025/02/19
    **/
    LikesPosts load(int id);

    /**
    * 查询 分页查询
    * @author capkin
    * @date 2025/02/19
    **/
    List<LikesPosts> pageList(int offset,int pagesize);

    /**
    * 查询 分页查询 count
    * @author capkin
    * @date 2025/02/19
    **/
    int pageListCount(int offset,int pagesize);

}
