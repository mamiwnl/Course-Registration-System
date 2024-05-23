from Lecturer import Lecturer

class Advisor(Lecturer):
    def __init__(self, lecturer=None, id=None, password=None, name=None, surname=None, title=None, supervisedStudentList=None):
        if lecturer is not None:
            super().__init__(lecturer.getId(), lecturer.getPassword(), lecturer.getName(), lecturer.getSurname(), lecturer.getTitle())
            self.__supervisedStudentList = []
        else:
            super().__init__(id, password, name, surname, title)
            self.__supervisedStudentList = supervisedStudentList if supervisedStudentList is not None else []

    def isSupervisingStudent(self, studentID):
        supervising = False
        studentIdAsInteger = 0
        try:
            studentIdAsInteger = int(studentID)
        except Exception as e:
            return False

        for student in self.__supervisedStudentList:
            if student.getId() == studentIdAsInteger:
                supervising = True

        return supervising

    def getSupervisedStudent(self, studentID):
        studentIdAsInteger = 0
        student = None
        try:
            studentIdAsInteger = int(studentID)
            for supervisedStudent in self.__supervisedStudentList:
                if supervisedStudent.getId() == studentIdAsInteger:
                    student = supervisedStudent
        except Exception as e:
            print()

        return student

    # Getters and Setters for attributes
    def getSupervisedStudentList(self):
        return self.__supervisedStudentList

    def setSupervisedStudentList(self, supervisedStudentList):
        self.__supervisedStudentList = supervisedStudentList

    def getInfo(self):
        # Implementation for viewing general advisor information
        return f"Advisor: {self.getTitle()} {self.getName()} {self.getSurname()}\n"
