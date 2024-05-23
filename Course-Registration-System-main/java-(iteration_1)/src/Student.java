import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student extends User  {
    private int semester;
    private String major;
    private int advisorID;
    private Advisor advisor;
    private ArrayList<CourseSection> currentCourses;
    private Transcript transcript;
    private ArrayList<CourseRequest> courseRequests;

    // Constructor

    public Student(int id, String password, String name, String surname, int semester, String major, int advisorID, Advisor advisor
                   , ArrayList<CourseSection> currentCourses, Transcript transcript, ArrayList<CourseRequest> courseRequests) {
        super(id, password, name, surname);
        this.semester = semester;
        this.major = major;
        this.advisorID = advisorID;
        this.advisor = advisor;
        this.currentCourses = currentCourses;
        this.transcript = transcript;
        this.courseRequests = courseRequests;
    }

    // Getter & Setter methods

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }
    public int getAdvisorID() {
        return advisorID;
    }
    public void setAdvisorID(int advisorID) {
        this.advisorID = advisorID;
    }
    public ArrayList<CourseSection> getCurrentCourses() {
        return currentCourses;
    }

    public void setCurrentCourses(ArrayList<CourseSection> currentCourses) {
        this.currentCourses = currentCourses;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public ArrayList<CourseRequest> getCourseRequests() {
        return courseRequests;
    }

    public void setCourseRequests(ArrayList<CourseRequest> courseRequests) {
        this.courseRequests = courseRequests;
    }

    public void save() {
        // implementation
    }

    @Override
    public String getInfo() {
        // Implementation for viewing general student information
        return ("\nStudent ID: " + getId() + "\nFull Name: " + getName() + " " + getSurname() + "Major: " + getMajor()
                + "\nSemester: "
                + getSemester() + "\nMajor: " + getMajor() + "\n"  + getAdvisor().getInfo()
                + "\n--------------------------");
    }


    // Lists student's current courses
    public String[] listCurrentCourses() {
        String[] currentCourseList = new String[50];
        if (currentCourses != null) {
            for (int i = 0; i < currentCourses.size(); i++) {
                currentCourseList[i] = currentCourses.get(i).viewCourseSectionInfo();
            }
        }
        return currentCourseList;
    }
}
