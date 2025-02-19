import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description league
 * @author capkin
 * @date 2025-02-19
 */
@Service
public class LeagueServiceImpl implements LeagueService {

	@Resource
	private LeagueMapper leagueMapper;


	@Override
	public Object insert(League league) {

		// valid
		if (league == null) {
			return ReturnT.error("必要参数缺失");
        }

		leagueMapper.insert(league);
        return ReturnT.success();
	}


	@Override
	public Object delete(int id) {
		int ret = leagueMapper.delete(id);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Object update(League league) {
		int ret = leagueMapper.update(league);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public League load(int id) {
		return leagueMapper.load(id);
	}


	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<League> pageList = leagueMapper.pageList(offset, pagesize);
		int totalCount = leagueMapper.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
