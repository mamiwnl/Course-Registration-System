import os
import json
from Transcript import Transcript
from Student import Student
from Course import Course
from Advisor import Advisor
from Lecturer import Lecturer
from CourseSection import CourseSection
from StudentController import StudentController
from AdvisorController import AdvisorController
from TranscriptReader import readTranscriptFromFile
from StudentReader import readStudentsFromFile
from LecturerReader import readLecturersFromJsonFile
import logging

class CourseRegistrationSystem:
   
    def __init__(self):
        self.__transcripts = []
        self.__students = []
        self.__courses = []
        self.__lecturers = []
        self.__advisors = []
        self.logger = logging.getLogger(__name__)
        logging.basicConfig(level=logging.INFO)

    def createTranscriptList(self):
        directoryPath =  "./iteration-3/data/Transcripts"

        try:
            # List all files in the directory
            file_paths = [os.path.join(directoryPath, file) for file in os.listdir(directoryPath)]

            # Iterate through each file and read the transcript
            for file_path in file_paths:
                transcript = readTranscriptFromFile(file_path)
                self.__transcripts.append(transcript)
        except Exception as e:
            print(f"An error occurred: {e}")  # Handle the exception according to your needs

    def createStudentList(self):
        file_path = "./iteration-3/data/student.json"
        try:
            self.__students = readStudentsFromFile(file_path)
        except Exception as e:
            print(f"An error occurred: {e}")  # Handle the exception according to your needs

    def assignTranscripts(self):
        for student in self.__students:
            for transcript in self.__transcripts:

                if transcript.getId() == student.getId():
                    student.setTranscript(transcript)

    def createCourseList(self):
        course_path = "./iteration-3/data/course.json"
        with open(course_path, 'r') as file:
            course_data = json.load(file)
            self.__courses = [Course(
                course['courseName'],
                course['courseCode'],
                course['courseSemester'],
                course['courseType'],
                course['credit'],
                [CourseSection(
                    course['courseName'],
                    course['courseCode'],
                    course['courseSemester'],
                    course['courseType'],
                    course['credit'],
                    None,
                    course['prerequisite'],
                    course['lecturer'],
                    section['courseSectionCode'],
                    section['classroom'],
                    section['capacity'],
                    section['noOfEnrolledStudent'],
                    section['day'],
                    section['hour']
                ) for section in course['courseSections']],
                course['prerequisite'],
                course['lecturer']
            ) for course in course_data]


    def createLecturerList(self):
        file_path = "./iteration-3/data/lecturer.json"
        with open(file_path, 'r') as file:
            lecturer_data = json.load(file)
            self.__lecturers = readLecturersFromJsonFile(file_path)
           
    def assignLecturerToCourses(self):
         self.__courses[0].setLecturer(self.__lecturers[0])
         self.__courses[1].setLecturer(self.__lecturers[1])
         self.__courses[2].setLecturer(self.__lecturers[2])
         self.__courses[3].setLecturer(self.__lecturers[3]) 
         self.__courses[4].setLecturer(self.__lecturers[4]) 
         self.__courses[5].setLecturer(self.__lecturers[5])
         self.__courses[6].setLecturer(self.__lecturers[6])
         self.__courses[7].setLecturer(self.__lecturers[7]) 
         self.__courses[8].setLecturer(self.__lecturers[8])
         self.__courses[9].setLecturer(self.__lecturers[9])
         self.__courses[10].setLecturer(self.__lecturers[10])
         self.__courses[11].setLecturer(self.__lecturers[1]) 
         self.__courses[12].setLecturer(self.__lecturers[11])
         self.__courses[13].setLecturer(self.__lecturers[8])
         self.__courses[14].setLecturer(self.__lecturers[9]) 
         self.__courses[15].setLecturer(self.__lecturers[5]) 
         self.__courses[16].setLecturer(self.__lecturers[10])


    def assignPrerequisitesToCourses(self):
        self.__courses[11].getPrerequisite().append(self.__courses[0]) # Operating System
        self.__courses[10].getPrerequisite().append(self.__courses[0]) # Database
        self.__courses[14].getPrerequisite().append(self.__courses[9]) # Modelling
        self.__courses[16].getPrerequisite().append(self.__courses[10]) # Advanced Unix
        self.__courses[18].getPrerequisite().append(self.__courses[3]) # Financial Engineering

    def createAdvisor(self):
        for lecturer in self.__lecturers[:5]:
            advisor = Advisor(lecturer)
            self.__advisors.append(advisor)

    def assignAdvisorToStudents(self):
        for student in self.__students:
            for advisor in self.__advisors:
                if student.getAdvisorId() == advisor.getId():
                    student.setAdvisor(advisor)
                    advisor.getSupervisedStudentList().append(student)


    def login(self):
        while True:
            print("----- Welcome to Marmara University Course Registration System -----")
            print("Type [1] to log in as advisor")
            print("Type [2] to log in as student")
            print("Type [q] to quit")
            selection = input("-> ")

            if selection not in ["1", "2", "q"]:
                continue

            if selection == "q":
                exit()
            elif selection == "1":
                self.advisorAuthentication()
            elif selection == "2":
                self.studentAuthentication()

    def studentAuthentication(self):
        while True:
            try:
                id = int(input("Please enter your id: "))
                password = input("Please enter your password: ")

                for i, student in enumerate(self.__students):
                    if id == student.getId() and password == student.getPassword():
                        self.logger.info("Authentication successful for student with ID: %d", id)
                        studentController = StudentController(student, self.__courses)
                        studentController.showOptions()
                        return
                self.logger.warning("Authentication failed for student with ID: %d", id)
                print("\nIncorrect user ID or password\n")
            except ValueError:
                self.logger.error("Invalid ID entered")
                print("\nPlease enter a valid ID\n")

    def advisorAuthentication(self):
        loginStatus = False
        currentAdvisorIndex = 0

        while not loginStatus:
            try:
                advisorId = int(input("Please enter your id: "))
                password = input("Please enter your password: ")

                for i, advisor in enumerate(self.__advisors):
                    if advisorId.__eq__(advisor.getId()) and password.__eq__(advisor.getPassword()) :
                        self.logger.info("Authentication successful for advisor with ID: %d", advisor.getId())
                        loginStatus = True
                        currentAdvisorIndex = i
                        break

                if loginStatus:
                    advisorController = AdvisorController(self.__advisors[currentAdvisorIndex], self.__courses)
                    advisorController.showOptions()
                else:
                    self.logger.warning("Authentication failed for advisor with ID: %d", advisorId)

            except ValueError:
                print("Please enter a valid ID")

    def getStudents(self):
        return self.__students

    def setStudents(self, students):
        self.__students = students

    def getTranscripts(self):
        return self.__transcripts

    def setTranscripts(self, transcripts):
        self.__transcripts = transcripts

    def getCourses(self):
        return self.__courses

    def setCourses(self, courses):
        self.__courses = courses

    def getLecturers(self):
        return self.__lecturers

    def setLecturers(self, lecturers):
        self.__lecturers = lecturers

    def getAdvisors(self):
        return self.__advisors

    def setAdvisors(self, advisors):
        self.__advisors = advisors
