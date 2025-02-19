import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description admin
 * @author capkin
 * @date 2025-02-19
 */
@Service
public class AdminServiceImpl implements AdminService {

	@Resource
	private AdminMapper adminMapper;


	@Override
	public Object insert(Admin admin) {

		// valid
		if (admin == null) {
			return ReturnT.error("必要参数缺失");
        }

		adminMapper.insert(admin);
        return ReturnT.success();
	}


	@Override
	public Object delete(int id) {
		int ret = adminMapper.delete(id);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Object update(Admin admin) {
		int ret = adminMapper.update(admin);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Admin load(int id) {
		return adminMapper.load(id);
	}


	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<Admin> pageList = adminMapper.pageList(offset, pagesize);
		int totalCount = adminMapper.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
