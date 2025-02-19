import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * @description report_comments_news
 * @author capkin
 * @date 2025-02-19
 */
public class ReportCommentsNews implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * cn_id
    */
    private Long cnId;


    public ReportCommentsNews() {
    }

    public Long getCnId() {
        return cnId;
    }

    public void setCnId(Long cnId) {
        this.cnId = cnId;
    }

}
