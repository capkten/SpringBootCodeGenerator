import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
/**
 * @description admin
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private SQLManager sqlManager;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(Admin admin){
        Admin admin=sqlManager.unique(Admin.class,admin.getId());
        if(admin!=null){
            sqlManager.updateById(admin);
            return ReturnT.success("编辑成功");
        }else{
            sqlManager.insert(admin);
            return ReturnT.error("保存成功");
        }
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        Admin admin=sqlManager.unique(Admin.class,id);
        if(admin!=null){
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
        Admin admin=sqlManager.unique(Admin.class,id);
        if(admin!=null){
            return ReturnT.success(admin);
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(Admin admin,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            List<Admin> list = sqlManager.query(Admin.class).select();
            return ReturnT.success(list);
    }

}
