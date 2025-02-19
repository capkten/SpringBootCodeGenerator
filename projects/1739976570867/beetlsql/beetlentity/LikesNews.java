import java.io.Serializable;
import lombok.Data;
import java.util.Date;
import java.util.List;
/**
 * @description likes_news
 * @author capkin
 * @date 2025-02-19
 */
@Data
@ApiModel("likes_news")
public class LikesNews implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @ApiModelProperty("id")
    private Long id;

    public LikesNews() {
    }

}
