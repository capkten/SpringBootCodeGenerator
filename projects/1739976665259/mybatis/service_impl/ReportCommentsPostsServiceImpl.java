import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description report_comments_posts
 * @author capkin
 * @date 2025-02-19
 */
@Service
public class ReportCommentsPostsServiceImpl implements ReportCommentsPostsService {

	@Resource
	private ReportCommentsPostsMapper reportCommentsPostsMapper;


	@Override
	public Object insert(ReportCommentsPosts reportCommentsPosts) {

		// valid
		if (reportCommentsPosts == null) {
			return ReturnT.error("必要参数缺失");
        }

		reportCommentsPostsMapper.insert(reportCommentsPosts);
        return ReturnT.success();
	}


	@Override
	public Object delete(int id) {
		int ret = reportCommentsPostsMapper.delete(id);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Object update(ReportCommentsPosts reportCommentsPosts) {
		int ret = reportCommentsPostsMapper.update(reportCommentsPosts);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public ReportCommentsPosts load(int id) {
		return reportCommentsPostsMapper.load(id);
	}


	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<ReportCommentsPosts> pageList = reportCommentsPostsMapper.pageList(offset, pagesize);
		int totalCount = reportCommentsPostsMapper.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
