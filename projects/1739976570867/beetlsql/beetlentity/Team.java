import java.io.Serializable;
import lombok.Data;
import java.util.Date;
import java.util.List;
/**
 * @description team
 * @author capkin
 * @date 2025-02-19
 */
@Data
@ApiModel("team")
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @ApiModelProperty("id")
    private Long id;

    public Team() {
    }

}
