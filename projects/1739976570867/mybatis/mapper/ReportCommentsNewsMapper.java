import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @description report_comments_news
 * @author capkin
 * @date 2025-02-19
 */
@Mapper
@Repository
public interface ReportCommentsNewsMapper {

    /**
    * 新增
    * @author capkin
    * @date 2025/02/19
    **/
    int insert(ReportCommentsNews reportCommentsNews);

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
    int update(ReportCommentsNews reportCommentsNews);

    /**
    * 查询 根据主键 id 查询
    * @author capkin
    * @date 2025/02/19
    **/
    ReportCommentsNews load(int id);

    /**
    * 查询 分页查询
    * @author capkin
    * @date 2025/02/19
    **/
    List<ReportCommentsNews> pageList(int offset,int pagesize);

    /**
    * 查询 分页查询 count
    * @author capkin
    * @date 2025/02/19
    **/
    int pageListCount(int offset,int pagesize);

}
