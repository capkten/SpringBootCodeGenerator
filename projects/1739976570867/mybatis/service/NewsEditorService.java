import java.util.Map;
/**
 * @description news_editor
 * @author capkin
 * @date 2025-02-19
 */
public interface NewsEditorService {

    /**
    * 新增
    */
    public Object insert(NewsEditor newsEditor);

    /**
    * 删除
    */
    public Object delete(int id);

    /**
    * 更新
    */
    public Object update(NewsEditor newsEditor);

    /**
    * 根据主键 id 查询
    */
    public NewsEditor load(int id);

    /**
    * 分页查询
    */
    public Map<String,Object> pageList(int offset, int pagesize);

}
