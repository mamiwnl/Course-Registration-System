
public class Lecturer extends Staff {
    private String title;


    public Lecturer(int id, String password, String name, String surname, String title) {
        super(id, password, name, surname);
        this.title=title;

    }

    // Getters and Setters for attributes
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    @Override
    public String getInfo() {
        // Implementation for viewing general lecturer information
        return("Lecturer:" + getTitle() + " " + getName() + " " + getSurname());
    }



}
