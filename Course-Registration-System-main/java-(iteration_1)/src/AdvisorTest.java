import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AdvisorTest {

    @Test
    void testIsSupervisingStudent() {
        Lecturer lecturer = new Lecturer(1, "pass1", "Cem Eren", "Kula", "Doctor");
        Advisor advisor = new Advisor(lecturer);

        ArrayList<Student> supervisedStudents = new ArrayList<>();
        supervisedStudents.add(new Student(150120001, "randompass1", "Deniz", "Avci", 5, "Computer Engineering", 1, advisor, new ArrayList<>(), new Transcript(), new ArrayList<>()));
        supervisedStudents.add(new Student(150120002, "randompass2", "Burak", "Gunduz", 5, "Computer Engineering", 2, advisor, new ArrayList<>(), new Transcript(), new ArrayList<>()));

        advisor.setSupervisedStudentList(supervisedStudents);

        assertTrue(advisor.isSupervisingStudent("150120001"));
        assertFalse(advisor.isSupervisingStudent("150120003"));
        assertFalse(advisor.isSupervisingStudent("invalid"));
    }

    @Test
    void testGetSupervisedStudent() {
        Lecturer lecturer = new Lecturer(1, "pass1", "Cem Eren", "Kula", "Doctor");
        Advisor advisor = new Advisor(lecturer);

        ArrayList<Student> supervisedStudents = new ArrayList<>();
        supervisedStudents.add(new Student(150120001, "randompass1", "Deniz", "Avci", 5, "Computer Engineering", 1, advisor, new ArrayList<>(), new Transcript(), new ArrayList<>()));
        supervisedStudents.add(new Student(150120002, "randompass2", "Burak", "Gunduz", 5, "Computer Engineering", 2, advisor, new ArrayList<>(), new Transcript(), new ArrayList<>()));

        advisor.setSupervisedStudentList(supervisedStudents);

        assertEquals("Deniz", advisor.getSupervisedStudent("150120001").getName());
        assertNull(advisor.getSupervisedStudent("150120003"));
        assertNull(advisor.getSupervisedStudent("invalid"));
    }

    @Test
    void testGetInfo() {
        Lecturer lecturer = new Lecturer(1, "pass1", "Cem Eren", "Kula", "Doctor");
        Advisor advisor = new Advisor(lecturer);

        assertEquals("Advisor: " + lecturer.getTitle() + " " + lecturer.getName() + " " + lecturer.getSurname(),
                advisor.getInfo());
    }

    @Test
    void testSetSupervisedStudentList() {
        Lecturer lecturer = new Lecturer(1, "pass1", "Cem Eren", "Kula", "Doctor");
        Advisor advisor = new Advisor(lecturer);

        ArrayList<Student> supervisedStudents = new ArrayList<>();
        supervisedStudents.add(new Student(150120001, "randompass1", "Deniz", "Avci", 5, "Computer Engineering", 1, advisor, new ArrayList<>(), new Transcript(), new ArrayList<>()));
        supervisedStudents.add(new Student(150120002, "randompass2", "Burak", "Gunduz", 5, "Computer Engineering", 2, advisor, new ArrayList<>(), new Transcript(), new ArrayList<>()));

        advisor.setSupervisedStudentList(supervisedStudents);

        assertEquals(supervisedStudents, advisor.getSupervisedStudentList());
    }
}


