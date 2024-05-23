from Course import Course

class CourseSection(Course):
    def __init__(self, courseName, courseCode, courseSemester, courseType, credit, courseSections, prerequisite, lecturer,
                 courseSectionCode, classroom, capacity, noOfEnrolledStudent, day, hour):
        
        super().__init__(courseName, courseCode, courseSemester, courseType, credit, courseSections, prerequisite, lecturer)
        self.__courseSectionCode = courseSectionCode
        self.__classroom = classroom
        self.__capacity = capacity
        self.__noOfEnrolledStudent = noOfEnrolledStudent
        self.__day = day
        self.__hour = hour

    def setCourseSectionCode(self, courseSectionCode):
        self.courseSectionCode = courseSectionCode

    def getClassroom(self):
        return self.__classroom

    def setClassroom(self, classroom):
        self.__classroom = classroom

    def getCapacity(self):  
        return self.__capacity

    def setCapacity(self, capacity):
        self.__capacity = capacity

    def getDay(self): 
        return self.__day

    def setDay(self, day):  
        self.__day = day

    def getHour(self): 
        return self.__hour

    def setHour(self, hour):  
        self.__hour = hour

    def getNoOfEnrolledStudents(self):  
        return self.__noOfEnrolledStudent

    def setNoOfEnrolledStudents(self, noOfEnrolledStudent):  
        self.__noOfEnrolledStudent = noOfEnrolledStudent

    def getCourseSectionCode(self):
        return self.__courseSectionCode
    
    def viewCourseSectionInfo(self):
        return (
            f"{self.viewCourseInfo()}\nSection info: {self.getCourseSectionCode()}\n"
            f"Classroom: {self.getClassroom()}\nHour: {self.getHour()}\n"
        )
        
