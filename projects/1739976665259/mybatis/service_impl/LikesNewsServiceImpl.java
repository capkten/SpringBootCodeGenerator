import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description likes_news
 * @author capkin
 * @date 2025-02-19
 */
@Service
public class LikesNewsServiceImpl implements LikesNewsService {

	@Resource
	private LikesNewsMapper likesNewsMapper;


	@Override
	public Object insert(LikesNews likesNews) {

		// valid
		if (likesNews == null) {
			return ReturnT.error("必要参数缺失");
        }

		likesNewsMapper.insert(likesNews);
        return ReturnT.success();
	}


	@Override
	public Object delete(int id) {
		int ret = likesNewsMapper.delete(id);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Object update(LikesNews likesNews) {
		int ret = likesNewsMapper.update(likesNews);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public LikesNews load(int id) {
		return likesNewsMapper.load(id);
	}


	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<LikesNews> pageList = likesNewsMapper.pageList(offset, pagesize);
		int totalCount = likesNewsMapper.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
