import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description team
 * @author capkin
 * @date 2025-02-19
 */
@Service
public class TeamServiceImpl implements TeamService {

	@Resource
	private TeamMapper teamMapper;


	@Override
	public Object insert(Team team) {

		// valid
		if (team == null) {
			return ReturnT.error("必要参数缺失");
        }

		teamMapper.insert(team);
        return ReturnT.success();
	}


	@Override
	public Object delete(int id) {
		int ret = teamMapper.delete(id);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Object update(Team team) {
		int ret = teamMapper.update(team);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Team load(int id) {
		return teamMapper.load(id);
	}


	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<Team> pageList = teamMapper.pageList(offset, pagesize);
		int totalCount = teamMapper.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
