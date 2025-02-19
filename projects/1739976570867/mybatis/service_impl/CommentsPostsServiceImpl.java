import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description comments_posts
 * @author capkin
 * @date 2025-02-19
 */
@Service
public class CommentsPostsServiceImpl implements CommentsPostsService {

	@Resource
	private CommentsPostsMapper commentsPostsMapper;


	@Override
	public Object insert(CommentsPosts commentsPosts) {

		// valid
		if (commentsPosts == null) {
			return ReturnT.error("必要参数缺失");
        }

		commentsPostsMapper.insert(commentsPosts);
        return ReturnT.success();
	}


	@Override
	public Object delete(int id) {
		int ret = commentsPostsMapper.delete(id);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Object update(CommentsPosts commentsPosts) {
		int ret = commentsPostsMapper.update(commentsPosts);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public CommentsPosts load(int id) {
		return commentsPostsMapper.load(id);
	}


	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<CommentsPosts> pageList = commentsPostsMapper.pageList(offset, pagesize);
		int totalCount = commentsPostsMapper.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
