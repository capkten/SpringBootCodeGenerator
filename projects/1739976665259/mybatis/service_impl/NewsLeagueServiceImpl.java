import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description news_league
 * @author capkin
 * @date 2025-02-19
 */
@Service
public class NewsLeagueServiceImpl implements NewsLeagueService {

	@Resource
	private NewsLeagueMapper newsLeagueMapper;


	@Override
	public Object insert(NewsLeague newsLeague) {

		// valid
		if (newsLeague == null) {
			return ReturnT.error("必要参数缺失");
        }

		newsLeagueMapper.insert(newsLeague);
        return ReturnT.success();
	}


	@Override
	public Object delete(int id) {
		int ret = newsLeagueMapper.delete(id);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Object update(NewsLeague newsLeague) {
		int ret = newsLeagueMapper.update(newsLeague);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public NewsLeague load(int id) {
		return newsLeagueMapper.load(id);
	}


	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<NewsLeague> pageList = newsLeagueMapper.pageList(offset, pagesize);
		int totalCount = newsLeagueMapper.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
