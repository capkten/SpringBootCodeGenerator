import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description news
 * @author capkin
 * @date 2025-02-19
 */
@Service
public class NewsServiceImpl implements NewsService {

	@Resource
	private NewsMapper newsMapper;


	@Override
	public Object insert(News news) {

		// valid
		if (news == null) {
			return ReturnT.error("必要参数缺失");
        }

		newsMapper.insert(news);
        return ReturnT.success();
	}


	@Override
	public Object delete(int id) {
		int ret = newsMapper.delete(id);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Object update(News news) {
		int ret = newsMapper.update(news);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public News load(int id) {
		return newsMapper.load(id);
	}


	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<News> pageList = newsMapper.pageList(offset, pagesize);
		int totalCount = newsMapper.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
