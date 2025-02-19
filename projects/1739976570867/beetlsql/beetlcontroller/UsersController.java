import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
/**
 * @description users
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private SQLManager sqlManager;

    /**
    * 新增或编辑
    */
    @PostMapping("/save")
    public Object save(Users users){
        Users users=sqlManager.unique(Users.class,users.getId());
        if(users!=null){
            sqlManager.updateById(users);
            return ReturnT.success("编辑成功");
        }else{
            sqlManager.insert(users);
            return ReturnT.error("保存成功");
        }
    }

    /**
    * 删除
    */
    @PostMapping("/delete")
    public Object delete(int id){
        Users users=sqlManager.unique(Users.class,id);
        if(users!=null){
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
        Users users=sqlManager.unique(Users.class,id);
        if(users!=null){
            return ReturnT.success(users);
        }else{
            return ReturnT.error("没有找到该对象");
        }
    }

    /**
    * 分页查询
    */
    @PostMapping("/list")
    public Object list(Users users,
                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(required = false, defaultValue = "10") int pageSize) {
            List<Users> list = sqlManager.query(Users.class).select();
            return ReturnT.success(list);
    }

}
