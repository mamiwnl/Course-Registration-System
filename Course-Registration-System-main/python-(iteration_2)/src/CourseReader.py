import json
from CourseSection import CourseSection
from Course import Course

class CourseReader:
    def read_courses_from_json_file(file_path):
        with open(file_path, 'r') as file:
            data = json.load(file)
            courses = []

            for course_data in data:
                course_name = course_data['courseName']
                course_code = course_data['courseCode']
                course_semester = course_data['courseSemester']
                course_type = course_data['courseType']
                credit = course_data['credit']

                sections = []
                for section_data in course_data['courseSections']:
                    course_section = CourseSection(
                        section_data['courseSectionCode'],
                        section_data['classroom'],
                        section_data['capacity'],
                        section_data['noOfEnrolledStudent'],
                        section_data['day'],
                        section_data['hour']
                    )
                    sections.append(course_section)

                prerequisite = course_data['prerequisite']
                lecturer = course_data['lecturer']

                course = Course(course_name, course_code, course_semester, course_type, credit, sections, prerequisite, lecturer)
                courses.append(course)

            return courses
