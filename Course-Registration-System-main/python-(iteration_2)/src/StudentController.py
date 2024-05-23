from RegistrationControl import RegistrationControl
from TranscriptSaver import TranscriptSaver
from CourseRequest import CourseRequest
from CourseSection import CourseSection
import logging

class StudentController:
    def __init__(self, student, courses):
        self.__student = student
        self.__courses = courses
        self.logger = logging.getLogger(__name__)
        logging.basicConfig(level=logging.INFO)

    def takeCoursesInput(self):
        courseSectionCodes = []
        try:
            numCourses = int(input("How many courses do you want to enroll? (Please type an integer): "))
        except ValueError:
            print("Error: Input is not an integer!")
            return []

        for i in range(numCourses):
            code = input(f"Enter the {i + 1}. course code with section no (e.g., CSE2225.1): ")
            courseSectionCodes.append(code)

        return courseSectionCodes

    @staticmethod
    def printCourseSchedule(sections):
        print("Course Schedule:")
        print("{:<15}{:<15}{:<15}".format("Classroom", "Day", "Hour"))
        print("-------------------------")

        for section in sections:
            print("{:<15}{:<15}{:<15}".format(section.getClassroom(), section.getDay(), section.getHour()))

    def showOptions(self):
        selection = ""
        while selection != "q":
            print("Type [1] Enroll a course")
            print("Type [2] List enrolled courses")
            print("Type [3] View Transcript")
            print("Type [4] View Info")
            print("Type [5] Requested courses info")
            print("Type [6] View Time Table")
            print("Type [q] to quit")
            selection = input("-> ")

            if selection == "1":
                self.enrollCourse()
            elif selection == "2":
                self.listEnrolledCourses()
            elif selection == "3":
                transcript = self.viewTranscript()

            elif selection == "4":
                print(self.__student.getInfo())
            elif selection == "5":
                self.viewRequestedCourses()
            elif selection == "6":
                self.printCourseSchedule(self.__student.getCurrentCourses())
            elif selection == "q":
                transcriptSaver = TranscriptSaver()
                transcriptSaver.save(self.__student.getTranscript())
            else:
                print("Wrong input! Please enter 1, 2, 3, 4, 5, 6, or q")

    def enrollCourse(self):
        registrationControl = RegistrationControl(self.__courses)
        availableCourses = registrationControl.getAvailableCourses(self.__student.getTranscript(), self.__student.getSemester())

        for course in availableCourses:
            for section in course.getCourseSections():
                print(section.viewCourseSectionInfo())

        requestedCourseSectionCodes = self.takeCoursesInput()

        for code in requestedCourseSectionCodes:
            if code is not None:
                if registrationControl.isCourseSectionCodeValid(availableCourses, code):
                    courseSection = registrationControl.getCourseSection(availableCourses, code)
                    if registrationControl.hasCapacity(courseSection):
                        if not registrationControl.isOverlapping(self.__student.getCurrentCourses(), courseSection):
                            if registrationControl.currentTotalCredits(self.__student.getCurrentCourses()) < 30:

                                courseRequest = CourseRequest(self.__student.getId(), courseSection, "waiting")
                                self.logger.info("Request for %s set as 'waiting' for student with ID: %d", courseRequest.getCourseSectionCode(), self.__student.getId())

                                if self.__student.getCourseRequests() is None:
                                    self.__student.setCourseRequests([courseRequest])
                                else:
                                    self.__student.getCourseRequests().append(courseRequest)
                            else:
                                print("Failed: Total credits taken should not exceed 30.0!")
                        else:
                            print(f"Failed: the course is overlapping!")
                    else:
                        print(f"Failed: the course section is full!")
                else:
                    print("Failed: Section code is not valid!")

    def listEnrolledCourses(self):
        for courseSection in self.__student.listCurrentCourses():
            if courseSection is not None:
                print(courseSection)

    def viewTranscript(self):
        for grade in self.__student.getTranscript().viewTranscript():
            if grade is not None:
                print(grade)

    def viewRequestedCourses(self):
        if self.__student.getCourseRequests() is not None:
            for request in self.__student.getCourseRequests():
                print(request.viewRequestInfo())
        else:
            print("There is no course request")
