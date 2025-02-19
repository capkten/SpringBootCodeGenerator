import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * @description news
 * @author capkin
 * @date 2025-02-19
 */
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private Long id;


    public News() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
