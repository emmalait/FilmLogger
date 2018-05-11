package filmlogger.domain;

/**
 * Class responsible for film functionality.
 * 
 * @author emmalait
 */

public class Film {
    private Integer id;
    private String name;
    private String year;

    public Film(Integer id, String name, String year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    /**
     * Method returns Film's toString in format "Film (YYYY)".
     * @return 
     */
    
    @Override
    public String toString() {
        return name + " (" + year + ')';
    }

}
