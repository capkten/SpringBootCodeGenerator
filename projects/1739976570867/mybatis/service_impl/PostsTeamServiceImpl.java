import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description posts_team
 * @author capkin
 * @date 2025-02-19
 */
@Service
public class PostsTeamServiceImpl implements PostsTeamService {

	@Resource
	private PostsTeamMapper postsTeamMapper;


	@Override
	public Object insert(PostsTeam postsTeam) {

		// valid
		if (postsTeam == null) {
			return ReturnT.error("必要参数缺失");
        }

		postsTeamMapper.insert(postsTeam);
        return ReturnT.success();
	}


	@Override
	public Object delete(int id) {
		int ret = postsTeamMapper.delete(id);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Object update(PostsTeam postsTeam) {
		int ret = postsTeamMapper.update(postsTeam);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public PostsTeam load(int id) {
		return postsTeamMapper.load(id);
	}


	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<PostsTeam> pageList = postsTeamMapper.pageList(offset, pagesize);
		int totalCount = postsTeamMapper.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
