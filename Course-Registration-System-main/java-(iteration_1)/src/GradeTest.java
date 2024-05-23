import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GradeTest {

    @Test
    public void testGradeConstructorAndGetters() {
        // Arrange
        String courseName = "Calculus I";
        String courseCode = "MATH1001";
        int semester = 1;
        double credit = 6.0;
        double numericGrade = 52.0;
        String letterGrade = "CC";

        // Act
        Grade grade = new Grade(courseName, courseCode, semester, credit, numericGrade, letterGrade);

        // Assert
        assertEquals(courseName, grade.getCourseName());
        assertEquals(courseCode, grade.getCourseCode());
        assertEquals(semester, grade.getSemester());
        assertEquals(credit, grade.getCredit());
        assertEquals(numericGrade, grade.getNumericGrade());
        assertEquals(letterGrade, grade.getLetterGrade());
    }

    @Test
    public void testGradeSetters() {
        // Arrange
        Grade grade = new Grade("Linear Algebra for Computer Engineering", "MATH2256", 2, 5.0, 73.0, "BB");

        // Act
        grade.setCourseName("Data Structures");
        grade.setCourseCode("CSE2255");
        grade.setSemester(3);
        grade.setCredit(7.0);
        grade.setNumericGrade(92.0);
        grade.setLetterGrade("AA");

        // Assert
        assertEquals("Data Structures", grade.getCourseName());
        assertEquals("CSE2255", grade.getCourseCode());
        assertEquals(3, grade.getSemester());
        assertEquals(7.0, grade.getCredit());
        assertEquals(92.0, grade.getNumericGrade());
        assertEquals("AA", grade.getLetterGrade());
    }
}
