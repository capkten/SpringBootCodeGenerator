import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description collect_news
 * @author capkin
 * @date 2025-02-19
 */
@Service
public class CollectNewsServiceImpl implements CollectNewsService {

	@Resource
	private CollectNewsMapper collectNewsMapper;


	@Override
	public Object insert(CollectNews collectNews) {

		// valid
		if (collectNews == null) {
			return ReturnT.error("必要参数缺失");
        }

		collectNewsMapper.insert(collectNews);
        return ReturnT.success();
	}


	@Override
	public Object delete(int id) {
		int ret = collectNewsMapper.delete(id);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Object update(CollectNews collectNews) {
		int ret = collectNewsMapper.update(collectNews);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public CollectNews load(int id) {
		return collectNewsMapper.load(id);
	}


	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<CollectNews> pageList = collectNewsMapper.pageList(offset, pagesize);
		int totalCount = collectNewsMapper.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
