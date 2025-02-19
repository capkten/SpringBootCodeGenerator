import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @description news_editor
 * @author capkin
 * @date 2025-02-19
 */
@Service
public class NewsEditorServiceImpl implements NewsEditorService {

	@Resource
	private NewsEditorMapper newsEditorMapper;


	@Override
	public Object insert(NewsEditor newsEditor) {

		// valid
		if (newsEditor == null) {
			return ReturnT.error("必要参数缺失");
        }

		newsEditorMapper.insert(newsEditor);
        return ReturnT.success();
	}


	@Override
	public Object delete(int id) {
		int ret = newsEditorMapper.delete(id);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public Object update(NewsEditor newsEditor) {
		int ret = newsEditorMapper.update(newsEditor);
		return ret>0?ReturnT.success():ReturnT.error();
	}


	@Override
	public NewsEditor load(int id) {
		return newsEditorMapper.load(id);
	}


	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<NewsEditor> pageList = newsEditorMapper.pageList(offset, pagesize);
		int totalCount = newsEditorMapper.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageList", pageList);
		result.put("totalCount", totalCount);

		return result;
	}

}
