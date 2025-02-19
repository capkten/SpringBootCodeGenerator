import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description posts_league
 * @author capkin
 * @date 2025-02-19
 */
@Service
public class PostsLeagueServiceImpl implements PostsLeagueService {

	@Resource
	private PostsLeagueMapper postsLeagueMapper;


	@Override
	public Object insert(PostsLeague postsLeague) {

		// valid
		if (postsLeague == null) {
			return ReturnT.error("必要参数缺失");
        }

		postsLeagueMapper.insert(postsLeague);
        return ReturnT.success();
	}


	@Override
	public Object delete(int id) {
		int ret = postsLeagueMapper.delete(id);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Object update(PostsLeague postsLeague) {
		int ret = postsLeagueMapper.update(postsLeague);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public PostsLeague load(int id) {
		return postsLeagueMapper.load(id);
	}


	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<PostsLeague> pageList = postsLeagueMapper.pageList(offset, pagesize);
		int totalCount = postsLeagueMapper.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
