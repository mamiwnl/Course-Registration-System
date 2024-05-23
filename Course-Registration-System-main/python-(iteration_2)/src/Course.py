class Course:
    """ The purpose of this class is to contain information representative of a course. """
    
    
    """ Constructor for creating a Course object with specified parameters. """
    def __init__(self, courseName, courseCode, courseSemester, courseType, credit,
                 courseSections, prerequisite, lecturer):
        
        self.__courseName = courseName
        self.__courseCode = courseCode
        self.__courseSemester = courseSemester
        self.__courseType = courseType
        self.__credit = credit
        self.__courseSections = courseSections
        self.__prerequisite = [] 
        self.__lecturer = lecturer

    # Getter and setter methods for each attribute
    def getCourseName(self):
        return self.__courseName

    def setCourseName(self, courseName):
        self.__courseName = courseName

    def getCourseCode(self):
        return self.__courseCode

    def setCourseCode(self, courseCode):
        self.__courseCode = courseCode

    def getCourseSemester(self):
        return self.__courseSemester

    def setCourseSemester(self, courseSemester):
        self.__courseSemester = courseSemester

    def getCourseType(self):
        return self.__courseType

    def setCourseType(self, courseType):
        self.__courseType = courseType

    def getCredit(self):
        return self.__credit

    def setCredit(self, credit):
        self.__credit = credit

    def getCourseSections(self):
        return self.__courseSections

    def setCourseSections(self, courseSections):
        self.__courseSections = courseSections

    def getPrerequisite(self):
        return self.__prerequisite

    def setPrerequisite(self, prerequisite):
        self.__prerequisite = prerequisite

    def getLecturer(self):
        return self.__lecturer

    def setLecturer(self, lecturer):
        self.__lecturer = lecturer

    
    """ Information about the course is displayed. """
    def viewCourseInfo(self):
        
        return (
            f"{self.getCourseCode()}  {self.getCourseName()}\n"
            f"Credit: {self.getCredit()}\n"
            f"Semester: {self.getCourseSemester()}\n"
            f"Course Type: {self.getCourseType()}"
        )
