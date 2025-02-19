import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description posts
 * @author capkin
 * @date 2025-02-19
 */
@Service
public class PostsServiceImpl implements PostsService {

	@Resource
	private PostsMapper postsMapper;


	@Override
	public Object insert(Posts posts) {

		// valid
		if (posts == null) {
			return ReturnT.error("必要参数缺失");
        }

		postsMapper.insert(posts);
        return ReturnT.success();
	}


	@Override
	public Object delete(int id) {
		int ret = postsMapper.delete(id);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Object update(Posts posts) {
		int ret = postsMapper.update(posts);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Posts load(int id) {
		return postsMapper.load(id);
	}


	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<Posts> pageList = postsMapper.pageList(offset, pagesize);
		int totalCount = postsMapper.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
