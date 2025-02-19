import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
/**
 * @description news_editor
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/newsEditor")
public class NewsEditorController {

    @Autowired
    private SQLManager sqlManager;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(NewsEditor newsEditor){
        NewsEditor newsEditor=sqlManager.unique(NewsEditor.class,newsEditor.getId());
        if(newsEditor!=null){
            sqlManager.updateById(newsEditor);
            return ReturnT.success("编辑成功");
        }else{
            sqlManager.insert(newsEditor);
            return ReturnT.error("保存成功");
        }
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        NewsEditor newsEditor=sqlManager.unique(NewsEditor.class,id);
        if(newsEditor!=null){
            sqlManager.deleteById(id);
            return ReturnT.success("删除成功");
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 查询
    */
    @PostMapping("/find")
    public Object find(int id){
        NewsEditor newsEditor=sqlManager.unique(NewsEditor.class,id);
        if(newsEditor!=null){
            return ReturnT.success(newsEditor);
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(NewsEditor newsEditor,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            List<NewsEditor> list = sqlManager.query(NewsEditor.class).select();
            return ReturnT.success(list);
    }

}
