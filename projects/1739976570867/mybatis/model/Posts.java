import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * @description posts
 * @author capkin
 * @date 2025-02-19
 */
public class Posts implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private Long id;


    public Posts() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
