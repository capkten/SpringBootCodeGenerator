import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * @description users
 * @author capkin
 * @date 2025-02-19
 */
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private Long id;


    public Users() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
