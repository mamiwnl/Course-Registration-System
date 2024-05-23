import json
from CourseRequest import CourseRequest
from CourseSection import CourseSection
from Student import Student

def readStudentsFromFile(file_path):
    with open(file_path, 'r', encoding='utf-8') as file:
        data = json.load(file)

    student_list = []

    for student_data in data:
        id = student_data['id']
        password = student_data['password']
        name = student_data['name']
        surname = student_data['surname']
        semester = student_data['semester']
        major = student_data['major']
        advisorID = student_data['advisorID']

        current_courses = []
        for course_data in student_data.get('currentCourses', []):
            section = CourseSection(
                course_data['courseName'], course_data['courseCode'],
                course_data['courseSemester'], course_data['courseType'], course_data['credit'], course_data['courseSections'], course_data['prerequisite'], course_data['lecturer'],
                course_data['courseSectionCode'], course_data['classroom'],
                course_data['capacity'], course_data['noOfEnrolledStudent'],
                course_data['day'], course_data['hour']
            )
            current_courses.append(section)

        requests = []
        for request_data in student_data.get('requests', []):
            course_data = request_data['courseSection']
            course_section = CourseSection(
                course_data['courseName'], course_data['courseCode'],
                course_data['courseSemester'], course_data['courseType'], course_data['credit'], course_data['courseSections'], course_data['prerequisite'], course_data['lecturer'],
                course_data['courseSectionCode'], course_data['classroom'],
                course_data['capacity'], course_data['noOfEnrolledStudent'],
                course_data['day'], course_data['hour']
            )
            request = CourseRequest(request_data['studentID'], course_section, request_data['status'])
            requests.append(request)



        student = Student(id, password, name, surname, semester, major, advisorID, None, current_courses,None, requests)

        student_list.append(student)

    return student_list
