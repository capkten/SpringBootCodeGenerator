import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * @description posts_team
 * @author capkin
 * @date 2025-02-19
 */
public class PostsTeam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private Long id;


    public PostsTeam() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
