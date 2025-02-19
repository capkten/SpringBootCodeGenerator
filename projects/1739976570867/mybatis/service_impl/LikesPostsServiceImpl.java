import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description likes_posts
 * @author capkin
 * @date 2025-02-19
 */
@Service
public class LikesPostsServiceImpl implements LikesPostsService {

	@Resource
	private LikesPostsMapper likesPostsMapper;


	@Override
	public Object insert(LikesPosts likesPosts) {

		// valid
		if (likesPosts == null) {
			return ReturnT.error("必要参数缺失");
        }

		likesPostsMapper.insert(likesPosts);
        return ReturnT.success();
	}


	@Override
	public Object delete(int id) {
		int ret = likesPostsMapper.delete(id);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Object update(LikesPosts likesPosts) {
		int ret = likesPostsMapper.update(likesPosts);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public LikesPosts load(int id) {
		return likesPostsMapper.load(id);
	}


	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<LikesPosts> pageList = likesPostsMapper.pageList(offset, pagesize);
		int totalCount = likesPostsMapper.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
