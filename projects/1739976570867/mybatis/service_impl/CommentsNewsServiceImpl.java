import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description comments_news
 * @author capkin
 * @date 2025-02-19
 */
@Service
public class CommentsNewsServiceImpl implements CommentsNewsService {

	@Resource
	private CommentsNewsMapper commentsNewsMapper;


	@Override
	public Object insert(CommentsNews commentsNews) {

		// valid
		if (commentsNews == null) {
			return ReturnT.error("必要参数缺失");
        }

		commentsNewsMapper.insert(commentsNews);
        return ReturnT.success();
	}


	@Override
	public Object delete(int id) {
		int ret = commentsNewsMapper.delete(id);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Object update(CommentsNews commentsNews) {
		int ret = commentsNewsMapper.update(commentsNews);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public CommentsNews load(int id) {
		return commentsNewsMapper.load(id);
	}


	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<CommentsNews> pageList = commentsNewsMapper.pageList(offset, pagesize);
		int totalCount = commentsNewsMapper.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
