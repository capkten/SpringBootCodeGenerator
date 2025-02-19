import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * @description likes_posts
 * @author capkin
 * @date 2025-02-19
 */
public class LikesPosts implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private Long id;


    public LikesPosts() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
