import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description posts_team
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface PostsTeamMapper {

    /**
    * 新增
    * @author capkin
    * @date 2025/02/19
    **/
    int insert(PostsTeam postsTeam);

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
    int update(PostsTeam postsTeam);

    /**
    * 查询 根据主键 id 查询
    * @author capkin
    * @date 2025/02/19
    **/
    PostsTeam load(int id);

    /**
    * 查询 分页查询
    * @author capkin
    * @date 2025/02/19
    **/
    List<PostsTeam> pageList(int offset,int pagesize);

    /**
    * 查询 分页查询 count
    * @author capkin
    * @date 2025/02/19
    **/
    int pageListCount(int offset,int pagesize);

}
