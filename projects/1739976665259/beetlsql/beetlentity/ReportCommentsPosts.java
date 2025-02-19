import java.io.Serializable;
import lombok.Data;
import java.util.Date;
import java.util.List;
/**
 * @description report_comments_posts
 * @author capkin
 * @date 2025-02-19
 */
@Data
@ApiModel("report_comments_posts")
public class ReportCommentsPosts implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * cp_id
    */
    @ApiModelProperty("cp_id")
    private Long cpId;

    public ReportCommentsPosts() {
    }

}
