public class Staff extends User {

    public Staff(int id, String password, String name, String surname) {
        super(id, password, name, surname);

    }

    @Override
    public String getInfo() {
        // Implementation for viewing general staff information
        return("Name: " + getName() + "\nSurname: " + getSurname());
    }

}


