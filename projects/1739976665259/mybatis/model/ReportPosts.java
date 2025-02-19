import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * @description report_posts
 * @author capkin
 * @date 2025-02-19
 */
public class ReportPosts implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * p_id
    */
    private Long pId;


    public ReportPosts() {
    }

    public Long getPId() {
        return pId;
    }

    public void setPId(Long pId) {
        this.pId = pId;
    }

}
