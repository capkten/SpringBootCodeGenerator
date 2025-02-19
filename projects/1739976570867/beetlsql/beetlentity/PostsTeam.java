import java.io.Serializable;
import lombok.Data;
import java.util.Date;
import java.util.List;
/**
 * @description posts_team
 * @author capkin
 * @date 2025-02-19
 */
@Data
@ApiModel("posts_team")
public class PostsTeam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @ApiModelProperty("id")
    private Long id;

    public PostsTeam() {
    }

}
