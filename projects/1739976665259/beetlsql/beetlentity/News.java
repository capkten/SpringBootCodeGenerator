import java.io.Serializable;
import lombok.Data;
import java.util.Date;
import java.util.List;
/**
 * @description news
 * @author capkin
 * @date 2025-02-19
 */
@Data
@ApiModel("news")
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @ApiModelProperty("id")
    private Long id;

    public News() {
    }

}
