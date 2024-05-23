import unittest
from unittest.mock import MagicMock
from Grade import Grade  # Import your Grade class
from Transcript import Transcript

class TestTranscript(unittest.TestCase):

    def setUp(self):
        # Create instances of Grade class for testing
        self.sample_grades = [
            Grade(courseCode="CSE101", courseName="Introduction to Programming", semester=1, credit=3, numericGrade=90, letterGrade="A"),
            Grade(courseCode="MAT102", courseName="Calculus", semester=1, credit=4, numericGrade=85, letterGrade="B+")
        ]

    def test_get_grades(self):
        transcript = Transcript(transcriptId=1, GPA=3.5, grades=self.sample_grades)
        result = transcript.getGrades()
        self.assertEqual(result, self.sample_grades)

    def test_set_grades(self):
        transcript = Transcript(transcriptId=1, GPA=3.5, grades=[])
        transcript.setGrades(self.sample_grades)
        result = transcript.getGrades()
        self.assertEqual(result, self.sample_grades)

    def test_get_id(self):
        transcript = Transcript(transcriptId=1, GPA=3.5, grades=self.sample_grades)
        result = transcript.getId()
        self.assertEqual(result, 1)

    def test_set_id(self):
        transcript = Transcript(transcriptId=1, GPA=3.5, grades=self.sample_grades)
        transcript.setId(2)
        result = transcript.getId()
        self.assertEqual(result, 2)

    def test_get_gpa(self):
        transcript = Transcript(transcriptId=1, GPA=3.5, grades=self.sample_grades)
        result = transcript.getGPA()
        self.assertEqual(result, 3.5)

    def test_set_gpa(self):
        transcript = Transcript(transcriptId=1, GPA=3.5, grades=self.sample_grades)
        transcript.setGPA(3.8)
        result = transcript.getGPA()
        self.assertEqual(result, 3.8)

    def test_save(self):
        transcript = Transcript(transcriptId=1, GPA=3.5, grades=self.sample_grades)
        transcript.save()  # Assuming this method doesn't raise errors during execution

    def test_view_transcript(self):
        transcript = Transcript(transcriptId=1, GPA=3.5, grades=self.sample_grades)
        result = transcript.viewTranscript()
        self.assertTrue(all(isinstance(item, str) for item in result))

if __name__ == '__main__':
    unittest.main()
