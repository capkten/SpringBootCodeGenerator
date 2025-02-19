import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * @description news_league
 * @author capkin
 * @date 2025-02-19
 */
public class NewsLeague implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * n_id
    */
    private Long nId;


    public NewsLeague() {
    }

    public Long getNId() {
        return nId;
    }

    public void setNId(Long nId) {
        this.nId = nId;
    }

}
