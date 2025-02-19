import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description report_comments_news
 * @author capkin
 * @date 2025-02-19
 */
@Service
public class ReportCommentsNewsServiceImpl implements ReportCommentsNewsService {

	@Resource
	private ReportCommentsNewsMapper reportCommentsNewsMapper;


	@Override
	public Object insert(ReportCommentsNews reportCommentsNews) {

		// valid
		if (reportCommentsNews == null) {
			return ReturnT.error("必要参数缺失");
        }

		reportCommentsNewsMapper.insert(reportCommentsNews);
        return ReturnT.success();
	}


	@Override
	public Object delete(int id) {
		int ret = reportCommentsNewsMapper.delete(id);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Object update(ReportCommentsNews reportCommentsNews) {
		int ret = reportCommentsNewsMapper.update(reportCommentsNews);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public ReportCommentsNews load(int id) {
		return reportCommentsNewsMapper.load(id);
	}


	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<ReportCommentsNews> pageList = reportCommentsNewsMapper.pageList(offset, pagesize);
		int totalCount = reportCommentsNewsMapper.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
