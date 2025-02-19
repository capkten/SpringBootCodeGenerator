import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description comments_posts
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface CommentsPostsMapper {

    /**
    * 新增
    * @author capkin
    * @date 2025/02/19
    **/
    int insert(CommentsPosts commentsPosts);

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
    int update(CommentsPosts commentsPosts);

    /**
    * 查询 根据主键 id 查询
    * @author capkin
    * @date 2025/02/19
    **/
    CommentsPosts load(int id);

    /**
    * 查询 分页查询
    * @author capkin
    * @date 2025/02/19
    **/
    List<CommentsPosts> pageList(int offset,int pagesize);

    /**
    * 查询 分页查询 count
    * @author capkin
    * @date 2025/02/19
    **/
    int pageListCount(int offset,int pagesize);

}
