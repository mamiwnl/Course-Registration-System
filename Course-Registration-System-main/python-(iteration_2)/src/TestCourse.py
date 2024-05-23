import unittest
from Course import Course  # Replace YourModuleName with the actual module name


class TestCourse(unittest.TestCase):

    def setUp(self):
        # Create a sample lecturer for testing
        self.sample_lecturer = "Sample Lecturer"

        # Create a sample course for testing
        self.sample_course = Course(
            courseName="Sample Course",
            courseCode="CSE101",
            courseSemester="Spring",
            courseType="Elective",
            credit=3.0,
            courseSections=[],
            prerequisite=[],
            lecturer=self.sample_lecturer
        )

    def test_get_course_name(self):
        self.assertEqual(self.sample_course.getCourseName(), "Sample Course")

    def test_set_course_name(self):
        self.sample_course.setCourseName("New Course Name")
        self.assertEqual(self.sample_course.getCourseName(), "New Course Name")

    def test_get_course_code(self):
        self.assertEqual(self.sample_course.getCourseCode(), "CSE101")

    def test_set_course_code(self):
        self.sample_course.setCourseCode("CSE102")
        self.assertEqual(self.sample_course.getCourseCode(), "CSE102")

    def test_get_course_semester(self):
        self.assertEqual(self.sample_course.getCourseSemester(), "Spring")

    def test_set_course_semester(self):
        self.sample_course.setCourseSemester("Fall")
        self.assertEqual(self.sample_course.getCourseSemester(), "Fall")

    def test_get_course_type(self):
        self.assertEqual(self.sample_course.getCourseType(), "Elective")

    def test_set_course_type(self):
        self.sample_course.setCourseType("Required")
        self.assertEqual(self.sample_course.getCourseType(), "Required")

    def test_get_credit(self):
        self.assertEqual(self.sample_course.getCredit(), 3.0)

    def test_set_credit(self):
        self.sample_course.setCredit(4.0)
        self.assertEqual(self.sample_course.getCredit(), 4.0)

    def test_get_course_sections(self):
        self.assertEqual(self.sample_course.getCourseSections(), [])

    def test_set_course_sections(self):
        new_sections = ["Section A", "Section B"]
        self.sample_course.setCourseSections(new_sections)
        self.assertEqual(self.sample_course.getCourseSections(), new_sections)

    def test_get_prerequisite(self):
        self.assertEqual(self.sample_course.getPrerequisite(), [])

    def test_set_prerequisite(self):
        new_prerequisite = ["Prerequisite A", "Prerequisite B"]
        self.sample_course.setPrerequisite(new_prerequisite)
        self.assertEqual(self.sample_course.getPrerequisite(), new_prerequisite)

    def test_get_lecturer(self):
        self.assertEqual(self.sample_course.getLecturer(), self.sample_lecturer)

    def test_set_lecturer(self):
        new_lecturer = "New Lecturer"
        self.sample_course.setLecturer(new_lecturer)
        self.assertEqual(self.sample_course.getLecturer(), new_lecturer)

    def test_view_course_info(self):
        expected_info = "CSE101  Sample Course\nCredit: 3.0\nSemester: Spring\nCourse Type: Elective"
        self.assertEqual(self.sample_course.viewCourseInfo(), expected_info)


if __name__ == '__main__':
    unittest.main()
