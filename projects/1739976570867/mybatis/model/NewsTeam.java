import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * @description news_team
 * @author capkin
 * @date 2025-02-19
 */
public class NewsTeam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * n_id
    */
    private Long nId;


    public NewsTeam() {
    }

    public Long getNId() {
        return nId;
    }

    public void setNId(Long nId) {
        this.nId = nId;
    }

}
