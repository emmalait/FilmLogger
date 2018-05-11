package filmlogger.domain;

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

    @Override
    public String toString() {
        return name + " (" + year + ')';
    }
    
    
    
}
