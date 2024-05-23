import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class CourseTest {

    @Test
    void testViewCourseInfo() {
        Lecturer lecturer = new Lecturer(1, "pass1", "Cem Eren", "Kula", "Doctor");

        ArrayList<CourseSection> courseSections = new ArrayList<>();
        ArrayList<Course> prerequisites = new ArrayList<>();

        Course course = new Course("Introduction to Java", "CS101", 1, "Core", 3, courseSections, prerequisites, lecturer);

        String expectedInfo = "CS101  Introduction to Java\nCredit: 3\nSemester: 1\nCourse Type: Core";

        assertEquals(expectedInfo, course.viewCourseInfo());
    }

    @Test
    void testSetCourseName() {
        Lecturer lecturer = new Lecturer(1, "pass1", "Cem Eren", "Kula", "Doctor");
        Course course = new Course("Introduction to Java", "CS101", 1, "Core", 3, new ArrayList<>(), new ArrayList<>(), lecturer);

        course.setCourseName("Advanced Java");

        assertEquals("Advanced Java", course.getCourseName());
    }

    @Test
    void testAddPrerequisite() {
        Lecturer lecturer = new Lecturer(1, "pass1", "Cem Eren", "Kula", "Doctor");
        Course course = new Course("Introduction to Java", "CS101", 1, "Core", 3, new ArrayList<>(), new ArrayList<>(), lecturer);

        Course prerequisiteCourse = new Course("Basic Programming", "CS100", 1, "Core", 3, new ArrayList<>(), new ArrayList<>(), lecturer);
        course.getPrerequisite().add(prerequisiteCourse);

        assertTrue(course.getPrerequisite().contains(prerequisiteCourse));
    }

    @Test
    void testViewCourseInfoWithLecturer() {
        Lecturer lecturer = new Lecturer(1, "pass1", "Cem Eren", "Kula", "Doctor");

        ArrayList<CourseSection> courseSections = new ArrayList<>();
        ArrayList<Course> prerequisites = new ArrayList<>();

        Course course = new Course("Introduction to Java", "CS101", 1, "Core", 3, courseSections, prerequisites, lecturer);

        String expectedInfo = "CS101  Introduction to Java\nCredit: 3\nSemester: 1\nCourse Type: Core";

        assertEquals(expectedInfo, course.viewCourseInfo());
    }

    @Test
    void testSetCredit() {
        Lecturer lecturer = new Lecturer(1, "pass1", "Cem Eren", "Kula", "Doctor");
        Course course = new Course("Introduction to Java", "CS101", 1, "Core", 3, new ArrayList<>(), new ArrayList<>(), lecturer);

        course.setCredit(4);

        assertEquals(4, course.getCredit());
    }
}


