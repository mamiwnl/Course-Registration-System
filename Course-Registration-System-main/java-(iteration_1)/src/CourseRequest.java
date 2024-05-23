import java.util.ArrayList;

// A request for a course section by a student
public class CourseRequest {
    private int studentID;
    private CourseSection courseSectionCode;
    private String status;

    

    // Constructor the objects
    public CourseRequest(int studentID, CourseSection courseSection, String status) {
        this.studentID = studentID;
        this.courseSectionCode = courseSection;
        this.status = status;
    }
    // Retrieving student ID
    public int getStudentID() {
        return studentID;
    }

    // Setting student ID
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    // Retrieving course section
    public CourseSection getCourseSection() {
        return courseSectionCode;
    }

    // Setting course section
    public void setCourseSection(CourseSection courseSection) {
        this.courseSectionCode = courseSection;
    }

    // Retrieving status
    public String getStatus() {
        return status;
    }

    // Setting status
    public void setStatus(String status) {
        this.status = status;
    }

    // Displaying request info
    public String  viewRequestInfo() {
        return ("Student ID: " + getStudentID() + "\n" + getCourseSection().viewCourseSectionInfo() +
                "\nStatus: " + getStatus() + "\n-------------");
    }
}
