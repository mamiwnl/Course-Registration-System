
    //This class represents the general information and grades of the course taken by the student.
    
    public class Grade {

    //Codes identifying course and grade information
    private String courseName;
    private String courseCode;
    private int semester;
    private double credit;
    private double numericGrade;
    private String letterGrade;
    
    //Constructor method for the Grade class.
    //Initializes the class properties with the provided values.
    public Grade(String courseName, String courseCode,int semester, double credit, double numericGrade, String letterGrade) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.semester=semester;
        this.credit =  credit;
        this.numericGrade = numericGrade;
        this.letterGrade = letterGrade;
    }

    //Getter and setter methods for each property.
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getNumericGrade() {
        return numericGrade;
    }

    public void setNumericGrade(double numericGrade) {
        this.numericGrade = numericGrade;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }
}
