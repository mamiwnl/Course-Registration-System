import java.util.ArrayList;
// Representing specific section of a course

public class CourseSection extends Course {
    private String courseSectionCode;
    private String classroom;
    private int capacity;
    private int noOfEnrolledStudent ;
    private String day;
    private String hour;
  // Course information and section specifics
   public CourseSection(String courseName, String courseCode, int courseSemester, String courseType, int credit, ArrayList<CourseSection> courseSections, ArrayList<Course> prerequisite, Lecturer lecturer, String courseSectionCode, String classroom, int capacity,int noOfEnrolledStudent, String day, String hour) {
        super(courseName, courseCode, courseSemester, courseType, credit, courseSections, prerequisite, lecturer);
        this.courseSectionCode = courseSectionCode;
        this.classroom = classroom;
        this.capacity = capacity;
        this.noOfEnrolledStudent=noOfEnrolledStudent;
        this.day = day;
        this.hour = hour;
    }
    // Retrieve section code

      public void setCourseSectionCode(String courseSectionCode) {
        this.courseSectionCode = courseSectionCode;
    }
    // Setting section code


    //Retrieving assigned classroom

    public String getClassroom() {
        return classroom;
    }
    // Setting assigned classroom

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
    // Retrieving section capacity

    public int getCapacity() {
        return capacity;
    }
    // Setting section capacity

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    // Retrieving scheduled day(s)

    public String getDay() {
        return day;
    }
    // Setting scheduled day(s)
    public void setDay(String day) {
        this.day = day;
    }
    // Retrieving scheduled hour
    public String getHour() {
        return hour;
    }
    // Setting scheduled hour

    public void setHour(String hour) {
        this.hour = hour;
    }

    public int getNoOfEnrolledStudent() {
        return noOfEnrolledStudent;
    }

    public void setNoOfEnrolledStudent(int noOfEnrolledStudent) {
        this.noOfEnrolledStudent = noOfEnrolledStudent;
    }

    public String getCourseSectionCode() {
        return courseSectionCode;
    }
    // Displaying section information, course details and section-specific details

   public String viewCourseSectionInfo(){
        return (viewCourseInfo() + "\nSection info: " +  getCourseSectionCode() + "\nClassroom: " + getClassroom() +
                "\nHour: " + getHour() + "\n");
    }
}
