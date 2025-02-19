import java.io.Serializable;
import lombok.Data;
import java.util.Date;
import java.util.List;
/**
 * @description news_league
 * @author capkin
 * @date 2025-02-19
 */
@Data
@ApiModel("news_league")
public class NewsLeague implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * n_id
    */
    @ApiModelProperty("n_id")
    private Long nId;

    public NewsLeague() {
    }

}
