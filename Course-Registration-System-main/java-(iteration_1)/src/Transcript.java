import java.util.ArrayList;

//Represents a student's academic transcript.

public class Transcript  {
    // Identifier for the transcript
    private int id;
    // GPA of the student
    private double GPA;
    // List of grades associated with the transcript
    private ArrayList<Grade> grades;

    // Constructs a Transcript object.
    public Transcript(int id, double GPA, ArrayList<Grade> grades) {
        this.id = id;
        this.GPA = GPA;
        this.grades = grades;
    }

    // Default constructor for the Transcript class.
    public Transcript() {
    }

    // Getter and setter methods for each property.
    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<Grade> grades) {
        this.grades = grades;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public void save() {
        // implementation
    }

    // Displays the contents of the transcript if available, otherwise indicates an
    // empty transcript.
    public String[] viewTranscript() {
        String[] transcriptInfo = new String[100];
        if (grades != null) {
            for (int i = 0; i < grades.size(); i++) {
                String temp = ("\nCourse Code: " + grades.get(i).getCourseCode() + "\nCourse Name: "
                        + grades.get(i).getCourseName() + "\nCredit: " + grades.get(i).getCredit() +
                        "\nNumeric Grade: " + grades.get(i).getNumericGrade() + "\nLetter Grade: "
                        + grades.get(i).getLetterGrade() + "\n--------------------------");
                transcriptInfo[i] = temp;
            }
        }
        return transcriptInfo;
    }
}
