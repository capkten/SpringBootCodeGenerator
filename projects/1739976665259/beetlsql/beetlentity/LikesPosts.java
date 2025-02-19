import java.io.Serializable;
import lombok.Data;
import java.util.Date;
import java.util.List;
/**
 * @description likes_posts
 * @author capkin
 * @date 2025-02-19
 */
@Data
@ApiModel("likes_posts")
public class LikesPosts implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @ApiModelProperty("id")
    private Long id;

    public LikesPosts() {
    }

}
