from UserController import UserController
from RegistrationControl import RegistrationControl
from CourseRequest import CourseRequest
from Grade import Grade
from TranscriptSaver import TranscriptSaver
from CourseSection import CourseSection
import logging

class AdvisorController(UserController):
    def __init__(self, advisor, courses):
        self.__advisor = advisor
        self.__registrationControl = RegistrationControl(courses)
        self.logger = logging.getLogger(__name__)
        logging.basicConfig(level=logging.INFO)

    def showOptions(self):
        while True:
            reply = ""
            print("Type [1] Approve/Reject course requests")
            print("Type [2] List supervised students")
            print("Type [3] View Info")
            print("Type [q] to quit")
            print("-> ", end="")

            try:
                reply = input()
            except Exception as e:
                print(str(e))
                break

            if reply == "q":
                break

            if reply == "1":
                self.startRegistrationApprovalProcess()
            elif reply == "2":
                self.listSupervisedStudents()
            elif reply == "3":
                print(self.__advisor.getInfo())
            else:
                print("Invalid response: " + reply)

    def startRegistrationApprovalProcess(self):
        while True:
            self.displayStudentRegistrations()

            print("To view student's registration, enter the student ID.")
            print("Press [q] to quit.")
            print("-> ", end="")
            reply = input()

            if reply == "q":
                break

            if self.__advisor.isSupervisingStudent(reply):
                student = self.__advisor.getSupervisedStudent(reply)
                print()
                print(student.getInfo())

                self.displayRegisteredCourses(student)

                while True:
                    print("Enter course code to change status or [q] to quit) \n-> ", end="")
                    reply = input()

                    if reply == "q":
                        break

                    if self.__registrationControl.isStudentRequestingCourseSection(student, reply):
                        courseRequest = CourseRequest(None, None, None)
                        sectionRequest = CourseSection(None, None, None, None, None, None, None, None, None, None, None, None, None, None)

                        for i in range(len(student.getCourseRequests())):
                            if student.getCourseRequests()[i].getCourseSection().getCourseSectionCode() == reply:
                                courseRequest = student.getCourseRequests()[i]
                                sectionRequest = student.getCourseRequests()[i].getCourseSection()

                        while True:
                            print("Change status to [a] approved or [r] rejected. Enter [q] to quit. \n-> ", end="")
                            reply = input()

                            if reply.lower() == "q":
                                break

                            if reply.lower() == "a":
                                self.logger.info("Request for %s approved for student with ID: %d", sectionRequest.getCourseSectionCode(), student.getId())
                                courseRequest.setStatus("approved")
                                temp = student.getCurrentCourses()
                                if temp is None:
                                    temp = []
                                temp.append(sectionRequest)
                                student.setCurrentCourses(temp)
                                grade = Grade(sectionRequest.getCourseName(), sectionRequest.getCourseCode(),
                                              sectionRequest.getCourseSemester(), sectionRequest.getCredit(), -1, "")
                                student.getTranscript().getGrades().append(grade)
                                transcriptSaver = TranscriptSaver()
                                transcriptSaver.save(student.getTranscript())
                                break
                            elif reply.lower() == "r":
                                self.logger.info("Request for %s rejected for student with ID: %d", sectionRequest.getCourseSectionCode(), student.getId())
                                courseRequest.setStatus("rejected")
                            else:
                                print("Invalid request.")

                    else:
                        print("Invalid course code")

            else:
                print("Invalid student ID: " + reply)

    def displayStudentRegistrations(self):
        for student in self.__advisor.getSupervisedStudentList():
            print(student.getId(), student.getName(), student.getSurname())
            if(student.getCourseRequests()!=None ):
                for request in student.getCourseRequests():
                    if request.getStatus()== "waiting":
                         print(" waiting approval")

    def displayRegisteredCourses(self, student):
        for i in range(len(student.getCourseRequests())):
            print("\t" + student.getCourseRequests()[i].getCourseSection().getCourseName() +
                  " " + student.getCourseRequests()[i].getCourseSection().getCourseSectionCode() +
                  ": " + student.getCourseRequests()[i].getStatus())

    def listSupervisedStudents(self):
        for i in range(len(self.__advisor.getSupervisedStudentList())):
            print(self.__advisor.getSupervisedStudentList()[i].getInfo())
