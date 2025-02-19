import java.io.Serializable;
import lombok.Data;
import java.util.Date;
import java.util.List;
/**
 * @description news_editor
 * @author capkin
 * @date 2025-02-19
 */
@Data
@ApiModel("news_editor")
public class NewsEditor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @ApiModelProperty("id")
    private Long id;

    public NewsEditor() {
    }

}
