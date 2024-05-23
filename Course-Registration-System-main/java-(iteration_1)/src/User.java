
//Represents a generic User with common attributes and methods.

//The necessary information about user
public abstract class User {
    private int id;
    private String password;
    private String name;
    private String surname;

    // Abstract method to view user information.
    public abstract String getInfo();

    // Constructor to initialize User attributes.
    public User(int id, String password, String name, String surname) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    // Getter and setter methods for each attribute.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
