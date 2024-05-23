class Grade:
    def __init__(self, courseName, courseCode, semester, credit, numericGrade, letterGrade):
        self.__courseName = courseName
        self.__courseCode = courseCode
        self.__semester = semester
        self.__credit = credit
        self.__numericGrade = numericGrade
        self.__letterGrade = letterGrade

    # Getter and setter methods
    def getCourseName(self):
        return self.__courseName
        
    def setCourseName(self, courseName):
        self.__courseName = courseName

    def getCourseCode(self):
        return self.__courseCode

    def setCourseCode(self, courseCode):
        self.__courseCode = courseCode
 
    def getSemester(self):
        return self.__semester

    def setSemester(self, semester):
        self.__semester = semester

    def getCredit(self):
        return self.__credit

    def setCredit(self, credit):
        self.__credit = credit

    def getNumericGrade(self):
        return self.__numericGrade

    def setNumericGrade(self, numericGrade):
        self.__numericGrade = numericGrade

    def getLetterGrade(self):
        return self.__letterGrade

    def setLetterGrade(self, letterGrade):
        self.__letterGrade = letterGrade
