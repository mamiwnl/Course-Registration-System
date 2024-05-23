import java.util.ArrayList;

public class Advisor extends Lecturer {
    private ArrayList<Student> supervisedStudentList;

    // Constructor
    public Advisor(Lecturer lecturer) {
        super(lecturer.getId(), lecturer.getPassword(), lecturer.getName(), lecturer.getSurname(), lecturer.getTitle());
        this.supervisedStudentList = new ArrayList<>();

    }

    public Advisor(int id, String password, String name, String surname, String title,
            ArrayList<Student> supervisedStudentList) {
        super(id, password, name, surname, title);
        this.supervisedStudentList = supervisedStudentList;

    }

    public Boolean isSupervisingStudent(String studentID) {
        Boolean supervising = false;
        int studentIdAsInteger = 0;
        try {
            studentIdAsInteger = Integer.parseInt(studentID);
        } catch (Exception e) {
            return false;
        }

        for (int i = 0; i < supervisedStudentList.size(); i++) {
            if (supervisedStudentList.get(i).getId() == studentIdAsInteger)
                supervising = true;
        }
        return supervising;
    }

    public Student getSupervisedStudent(String studentID) {
        int studentIdAsInteger = 0;
        Student student = null;
        try {
            studentIdAsInteger = Integer.parseInt(studentID);
            for (int i = 0; i < supervisedStudentList.size(); i++) {
                if (supervisedStudentList.get(i).getId() == studentIdAsInteger)
                    student = supervisedStudentList.get(i);
            }
        } catch (Exception e) {
            System.err.println();
        }
        return student;
    }

    // Getters and Setters for attributes
    public ArrayList<Student> getSupervisedStudentList() {
        return supervisedStudentList;
    }

    public void setSupervisedStudentList(ArrayList<Student> supervisedStudentList) {
        this.supervisedStudentList = supervisedStudentList;
    }

    @Override
    public String getInfo() {
        // Implementation for viewing general advisor information
        return "Advisor: " + getTitle() + " " + getName() + " " + getSurname();
    }
}
