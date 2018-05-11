
package filmlogger.domain;

/**
 * Class responsible for tag functionality.
 * 
 * @author emmalait
 */

public class Tag {
    private Integer id;
    private String name;
    
    public Tag(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
}
