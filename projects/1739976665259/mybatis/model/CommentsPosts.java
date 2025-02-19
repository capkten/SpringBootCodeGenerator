import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * @description comments_posts
 * @author capkin
 * @date 2025-02-19
 */
public class CommentsPosts implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private Long id;


    public CommentsPosts() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
