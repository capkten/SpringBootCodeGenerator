import java.io.Serializable;
import lombok.Data;
import java.util.Date;
import java.util.List;
/**
 * @description collect_news
 * @author capkin
 * @date 2025-02-19
 */
@Data
@ApiModel("collect_news")
public class CollectNews implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @ApiModelProperty("id")
    private Long id;

    public CollectNews() {
    }

}
