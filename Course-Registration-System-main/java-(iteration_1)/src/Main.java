import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        CourseRegistrationSystem systemManager = new CourseRegistrationSystem();
        systemManager.createTranscriptList();
        systemManager.createStudentList();
        systemManager.assignTranscripts();
        systemManager.createCourseList();
        systemManager.createLecturerList();
        System.out.println();
        systemManager.assignLecturerToCourses();

        systemManager.createAdvisor();

        systemManager.assignAdvisorToStudents();
        systemManager.assignPrerequsitiesToCourses();
        systemManager.login();
        

    }
}


