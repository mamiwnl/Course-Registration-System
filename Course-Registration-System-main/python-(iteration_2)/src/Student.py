from User import User
from Advisor import Advisor
from CourseSection import CourseSection
from Transcript import Transcript
from CourseRequest import CourseRequest
from StudentSaver import StudentSaver

class Student(User):
    def __init__(self, id, password, name, surname, semester, major, advisorID, advisor, currentCourses, transcript, courseRequests):
        super().__init__(id, password, name, surname)
        self.__semester = semester
        self.__major = major
        self.__advisorID = advisorID
        self.__advisor = advisor
        self.__currentCourses = currentCourses
        self.__transcript = transcript
        self.__courseRequests = courseRequests

    # Getter & Setter methods
    def getSemester(self):
        return self.__semester

    def setSemester(self, semester):
        self.__semester = semester

    def getMajor(self):
        return self.__major

    def setMajor(self, major):
        self.__major = major

    def getAdvisor(self):
        return self.__advisor

    def setAdvisor(self, advisor):
        self.__advisor = advisor

    def getAdvisorId(self):
        return self.__advisorID

    def setAdvisorId(self, advisorID):
        self.__advisorID = advisorID

    def getCurrentCourses(self):
        return self.__currentCourses

    def setCurrentCourses(self, currentCourses):
        self.__currentCourses = currentCourses

    def getTranscript(self):
        return self.__transcript

    def setTranscript(self, transcript):
        self.__transcript = transcript

    def getCourseRequests(self):
        return self.__courseRequests

    def setCourseRequests(self, courseRequests):
        self.__courseRequests = courseRequests

    def save(self):
        StudentSaver.save(self.get_id(), self.get_current_courses(), self.get_course_requests())
        pass

    def getInfo(self):
        # Implementation for viewing general student information
        return ("\nStudent ID: " + str(self.getId()) + "\nFull Name: " + self.getName() + " " + self.getSurname() +
                "\nMajor: " + self.getMajor() + "\nSemester: " + str(self.getSemester()) +
                "\n" + self.getAdvisor().getInfo() +
                "\n--------------------------")

    # Lists student's current courses
    def listCurrentCourses(self):
        current_course_list = []
        if self.__currentCourses is not None:
            for course_section in self.__currentCourses:
                current_course_list.append(course_section.viewCourseSectionInfo())
        return current_course_list
