import java.io.Serializable;
import lombok.Data;
import java.util.Date;
import java.util.List;
/**
 * @description comments_posts
 * @author capkin
 * @date 2025-02-19
 */
@Data
@ApiModel("comments_posts")
public class CommentsPosts implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @ApiModelProperty("id")
    private Long id;

    public CommentsPosts() {
    }

}
