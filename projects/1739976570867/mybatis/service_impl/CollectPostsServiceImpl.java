import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description collect_posts
 * @author capkin
 * @date 2025-02-19
 */
@Service
public class CollectPostsServiceImpl implements CollectPostsService {

	@Resource
	private CollectPostsMapper collectPostsMapper;


	@Override
	public Object insert(CollectPosts collectPosts) {

		// valid
		if (collectPosts == null) {
			return ReturnT.error("必要参数缺失");
        }

		collectPostsMapper.insert(collectPosts);
        return ReturnT.success();
	}


	@Override
	public Object delete(int id) {
		int ret = collectPostsMapper.delete(id);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Object update(CollectPosts collectPosts) {
		int ret = collectPostsMapper.update(collectPosts);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public CollectPosts load(int id) {
		return collectPostsMapper.load(id);
	}


	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<CollectPosts> pageList = collectPostsMapper.pageList(offset, pagesize);
		int totalCount = collectPostsMapper.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
