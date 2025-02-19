import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description report_posts
 * @author capkin
 * @date 2025-02-19
 */
@Service
public class ReportPostsServiceImpl implements ReportPostsService {

	@Resource
	private ReportPostsMapper reportPostsMapper;


	@Override
	public Object insert(ReportPosts reportPosts) {

		// valid
		if (reportPosts == null) {
			return ReturnT.error("必要参数缺失");
        }

		reportPostsMapper.insert(reportPosts);
        return ReturnT.success();
	}


	@Override
	public Object delete(int id) {
		int ret = reportPostsMapper.delete(id);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Object update(ReportPosts reportPosts) {
		int ret = reportPostsMapper.update(reportPosts);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public ReportPosts load(int id) {
		return reportPostsMapper.load(id);
	}


	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<ReportPosts> pageList = reportPostsMapper.pageList(offset, pagesize);
		int totalCount = reportPostsMapper.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
