import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description users
 * @author capkin
 * @date 2025-02-19
 */
@Service
public class UsersServiceImpl implements UsersService {

	@Resource
	private UsersMapper usersMapper;


	@Override
	public Object insert(Users users) {

		// valid
		if (users == null) {
			return ReturnT.error("必要参数缺失");
        }

		usersMapper.insert(users);
        return ReturnT.success();
	}


	@Override
	public Object delete(int id) {
		int ret = usersMapper.delete(id);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Object update(Users users) {
		int ret = usersMapper.update(users);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Users load(int id) {
		return usersMapper.load(id);
	}


	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<Users> pageList = usersMapper.pageList(offset, pagesize);
		int totalCount = usersMapper.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
