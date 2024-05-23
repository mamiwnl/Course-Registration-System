class CourseRequest:
    """  A request for a course section by a student """


    """ Constructor for the objects """
    def __init__(self, studentID, courseSection, status):
        
        self.__studentID = studentID
        self.__courseSectionCode = courseSection
        self.__status = status
        
    def getCourseSectionCode(self):
        return self.__courseSectionCode.getCourseSectionCode()

    """ Retrieving student ID """
    def getStudentId(self):  
        return self.__studentID
    
    """ Setting student ID """
    def setStudentId(self, studentID):
        self.__studentID = studentID

    """ Retrieving course section """
    def getCourseSection(self):
        return self.__courseSectionCode
    
    """ Setting course section """
    def setCourseSection(self, courseSection):
        self.__courseSectionCode = courseSection

    """ Retrieving status """
    def getStatus(self):
        return self.__status
    
    """ Setting status """
    def setStatus(self, status):
        self.__status = status
    

    """ Displaying request info """
    def viewRequestInfo(self):
        
        return (
            f"Student ID: {self.getStudentId()}\n"
            f"{self.getCourseSection().viewCourseSectionInfo()}\n"
            f"Status: {self.getStatus()}\n-------------"
        )
