import java.io.Serializable;
import lombok.Data;
import java.util.Date;
import java.util.List;
/**
 * @description report_comments_news
 * @author capkin
 * @date 2025-02-19
 */
@Data
@ApiModel("report_comments_news")
public class ReportCommentsNews implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * cn_id
    */
    @ApiModelProperty("cn_id")
    private Long cnId;

    public ReportCommentsNews() {
    }

}
