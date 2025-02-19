import java.io.Serializable;
import lombok.Data;
import java.util.Date;
import java.util.List;
/**
 * @description report_posts
 * @author capkin
 * @date 2025-02-19
 */
@Data
@ApiModel("report_posts")
public class ReportPosts implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * p_id
    */
    @ApiModelProperty("p_id")
    private Long pId;

    public ReportPosts() {
    }

}
