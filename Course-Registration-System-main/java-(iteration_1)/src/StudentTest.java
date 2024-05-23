import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void testGetSemester() {
        Lecturer lecturer = new Lecturer(1, "pass1", "Cem Eren", "Kula", "Doctor");
        Advisor advisor = new Advisor(lecturer);
        Student student = new Student(150120001, "randompass1", "Deniz", "Avci", 5, "Computer Engineering", 1, advisor, new ArrayList<>(), new Transcript(), new ArrayList<>());
        student.setSemester(5);
        assertEquals(5, student.getSemester());
    }

    @Test
    void testSetMajor() {
        Lecturer lecturer = new Lecturer(1, "pass1", "Cem Eren", "Kula", "Doctor");
        Advisor advisor = new Advisor(lecturer);
        Student student = new Student(150120001, "randompass1", "Deniz", "Avci", 5, "Computer Engineering", 1, advisor, new ArrayList<>(), new Transcript(), new ArrayList<>());
        student.setMajor("Computer Science");
        assertEquals("Computer Science", student.getMajor());
    }

    @Test
    void testGetAdvisor() {
        Lecturer lecturer = new Lecturer(1, "pass1", "Cem Eren", "Kula", "Doctor");
        Advisor advisor = new Advisor(lecturer);
        Student student = new Student(150120001, "randompass1", "Deniz", "Avci", 5, "Computer Engineering", 1, advisor, new ArrayList<>(), new Transcript(), new ArrayList<>());
        student.setAdvisor(advisor);
        assertEquals(advisor, student.getAdvisor());
    }

    @Test
    void testSetAdvisorID() {
        Lecturer lecturer = new Lecturer(1, "pass1", "Cem Eren", "Kula", "Doctor");
        Advisor advisor = new Advisor(lecturer);
        Student student = new Student(150120001, "randompass1", "Deniz", "Avci", 5, "Computer Engineering", 1, advisor, new ArrayList<>(), new Transcript(), new ArrayList<>());
        student.setAdvisorID(1);
        assertEquals(1, student.getAdvisorID());
    }

    @Test
    void testGetCurrentCourses() {
        ArrayList<CourseSection> currentCourses = new ArrayList<>();
        Lecturer lecturer = new Lecturer(1, "pass1", "Cem Eren", "Kula", "Doctor");
        Advisor advisor = new Advisor(lecturer);

        Student student = new Student(150120001, "randompass1", "Deniz", "Avci", 5, "Computer Engineering", 1, advisor, new ArrayList<>(), new Transcript(), new ArrayList<>());
        student.setCurrentCourses(currentCourses);
        assertEquals(currentCourses, student.getCurrentCourses());
    }

    @Test
    void testSetTranscript() {
        Lecturer lecturer = new Lecturer(1, "pass1", "Cem Eren", "Kula", "Doctor");
        Advisor advisor = new Advisor(lecturer);
        Transcript transcript = new Transcript(150120001, 2.5, new ArrayList<Grade>());
        Student student = new Student(150120001, "randompass1", "Deniz", "Avci", 5, "Computer Engineering", 1, advisor, new ArrayList<>(), new Transcript(), new ArrayList<>());
        student.setTranscript(transcript);
        assertEquals(transcript, student.getTranscript());
    }

    @Test
    void testSetCourseRequests() {
        ArrayList<CourseRequest> courseRequests = new ArrayList<>();
        Lecturer lecturer = new Lecturer(1, "pass1", "Cem Eren", "Kula", "Doctor");
        Advisor advisor = new Advisor(lecturer);
        Student student = new Student(150120001, "randompass1", "Deniz", "Avci", 5, "Computer Engineering", 1, advisor, new ArrayList<>(), new Transcript(), new ArrayList<>());
        student.setCourseRequests(courseRequests);
        assertEquals(courseRequests, student.getCourseRequests());
    }

    @Test
    void testSave() {
        Lecturer lecturer = new Lecturer(1, "pass1", "Cem Eren", "Kula", "Doctor");
        Advisor advisor = new Advisor(lecturer);
        Student student = new Student(150120001, "randompass1", "Deniz", "Avci", 5, "Computer Engineering", 1, advisor, new ArrayList<>(), new Transcript(), new ArrayList<>());
        assertDoesNotThrow(() -> student.save());
    }

    @Test
    void testListCurrentCourses() {
        ArrayList<CourseSection> currentCourses = new ArrayList<>();
        Lecturer lecturer = new Lecturer(1, "pass1", "Cem Eren", "Kula", "Doctor");
        Advisor advisor = new Advisor(lecturer);

        Student student = new Student(150120001, "randompass1", "Deniz", "Avci", 5, "Computer Engineering", 1, advisor, new ArrayList<>(), new Transcript(), new ArrayList<>());
        student.setCurrentCourses(currentCourses);

        String[] result = student.listCurrentCourses();
        assertNotNull(result);
    }

    @Test
    void testGetInfo() {
        Lecturer lecturer = new Lecturer(1, "pass1", "Cem Eren", "Kula", "Doctor");
        Advisor advisor = new Advisor(lecturer);
        Student student = new Student(150120001, "randompass1", "Deniz", "Avci", 5, "Computer Engineering", 1, advisor, new ArrayList<>(), new Transcript(), new ArrayList<>());
        student.setAdvisor(advisor);

        String result = student.getInfo();
        assertNotNull(result);
        // Add more assertions based on your specific logic
    }
}
