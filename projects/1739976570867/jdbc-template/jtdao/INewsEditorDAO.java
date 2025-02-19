import java.util.List;
/**
 * @description news_editor
 * @author capkin
 * @date 2025-02-19
 */
public interface INewsEditorDAO {

    int add(NewsEditor newsEditor);

    int update(NewsEditor newsEditor);

    int delete(int id);

    NewsEditor findById(int id);

    List<NewsEditor> findAllList(Map<String,Object> param);

}
