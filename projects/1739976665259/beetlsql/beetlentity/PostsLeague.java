import java.io.Serializable;
import lombok.Data;
import java.util.Date;
import java.util.List;
/**
 * @description posts_league
 * @author capkin
 * @date 2025-02-19
 */
@Data
@ApiModel("posts_league")
public class PostsLeague implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @ApiModelProperty("id")
    private Long id;

    public PostsLeague() {
    }

}
