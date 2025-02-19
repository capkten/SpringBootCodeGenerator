import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description news_team
 * @author capkin
 * @date 2025-02-19
 */
@Service
public class NewsTeamServiceImpl implements NewsTeamService {

	@Resource
	private NewsTeamMapper newsTeamMapper;


	@Override
	public Object insert(NewsTeam newsTeam) {

		// valid
		if (newsTeam == null) {
			return ReturnT.error("必要参数缺失");
        }

		newsTeamMapper.insert(newsTeam);
        return ReturnT.success();
	}


	@Override
	public Object delete(int id) {
		int ret = newsTeamMapper.delete(id);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Object update(NewsTeam newsTeam) {
		int ret = newsTeamMapper.update(newsTeam);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public NewsTeam load(int id) {
		return newsTeamMapper.load(id);
	}


	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<NewsTeam> pageList = newsTeamMapper.pageList(offset, pagesize);
		int totalCount = newsTeamMapper.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
