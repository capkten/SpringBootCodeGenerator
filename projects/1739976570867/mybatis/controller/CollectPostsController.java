import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @description collect_posts
 * @author capkin
 * @date 2025-02-19
 */
@RestController
@RequestMapping(value = "/collectPosts")
public class CollectPostsController {

    @Resource
    private CollectPostsService collectPostsService;

    /**
    * 新增
    * @author capkin
    * @date 2025/02/19
    **/
    @RequestMapping("/insert")
    public Object insert(CollectPosts collectPosts){
        return collectPostsService.insert(collectPosts);
    }

    /**
    * 刪除
    * @author capkin
    * @date 2025/02/19
    **/
    @RequestMapping("/delete")
    public Object delete(int id){
        return collectPostsService.delete(id);
    }

    /**
    * 更新
    * @author capkin
    * @date 2025/02/19
    **/
    @RequestMapping("/update")
    public Object update(CollectPosts collectPosts){
        return collectPostsService.update(collectPosts);
    }

    /**
    * 查询 根据主键 id 查询
    * @author capkin
    * @date 2025/02/19
    **/
    @RequestMapping("/load")
    public Object load(int id){
        return collectPostsService.load(id);
    }

    /**
    * 查询 分页查询
    * @author capkin
    * @date 2025/02/19
    **/
    @RequestMapping("/pageList")
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                                        @RequestParam(required = false, defaultValue = "10") int pagesize) {
        return collectPostsService.pageList(offset, pagesize);
    }

}
