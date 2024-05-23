import java.util.ArrayList;

//The purpose of a class is to contain information representative of a course.

public class Course {
    //Attributes required for the courses
    private String courseName;
    private String courseCode;
    private int courseSemester;
    private String courseType;
    private int credit;
    private ArrayList<CourseSection> courseSections;
    private ArrayList<Course> prerequisite;
    private Lecturer lecturer;

    //Constructor for creating a Course object with specified parameters.
    public Course(String courseName, String courseCode, int courseSemester, String courseType, int credit,
                  ArrayList<CourseSection> courseSections, ArrayList<Course> prerequisite,Lecturer lecturer) {
        super();
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseSemester = courseSemester;
        this.courseType = courseType;
        this.credit = credit;
        this.courseSections = courseSections;
        this.prerequisite= new ArrayList<>();
        this.lecturer = lecturer;
    }

    //Getter and setter methods for each attribute.
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

    public int getCourseSemester() {
        return courseSemester;
    }

    public void setCourseSemester(int courseSemester) {
        this.courseSemester = courseSemester;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public ArrayList<CourseSection> getCourseSections() {
        return courseSections;
    }

    public void setCourseSections(ArrayList<CourseSection> courseSections) {
        this.courseSections = courseSections;
    }

    public ArrayList<Course> getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(ArrayList<Course> prerequisite) {
        this.prerequisite = prerequisite;
    }

    public Lecturer getLecturer() {
       return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
       this.lecturer = lecturer;
    }

    //Information about the course is displayed.
    public String viewCourseInfo() {
        return (getCourseCode()+ "  " + getCourseName() + "\nCredit: " + getCredit()
                + "\nSemester: " + getCourseSemester() + "\nCourse Type: " + getCourseType());
    }
}
