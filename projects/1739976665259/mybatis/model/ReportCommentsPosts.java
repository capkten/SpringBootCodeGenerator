import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * @description report_comments_posts
 * @author capkin
 * @date 2025-02-19
 */
public class ReportCommentsPosts implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * cp_id
    */
    private Long cpId;


    public ReportCommentsPosts() {
    }

    public Long getCpId() {
        return cpId;
    }

    public void setCpId(Long cpId) {
        this.cpId = cpId;
    }

}
