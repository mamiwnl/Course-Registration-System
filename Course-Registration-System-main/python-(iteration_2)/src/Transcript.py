from Grade import Grade
from TranscriptSaver import TranscriptSaver 
class Transcript:
    def __init__(self, transcriptId, GPA, grades):
        self.__transcriptId = transcriptId
        self.__GPA = GPA
        self.__grades = grades
        
    def getGrades(self):
        return self.__grades

    def setGrades(self, grades):
        self.__grades = grades

    def getId(self):
        return self.__transcriptId

    def setId(self, transcriptId):
        self.__transcriptId = transcriptId

    def getGPA(self):
        return self.__GPA

    def setGPA(self, GPA):
        self.__GPA = GPA

    def save(self):
        saver = TranscriptSaver()
        saver.save(self)
        pass

    def viewTranscript(self):
        transcriptInfo = []
        if self.__grades is not None:
            for grade in self.__grades:
                temp = ("\nCourse Code: " + str(grade.getCourseCode()) +
                        "\nCourse Name: " + grade.getCourseName() +
                        "\nCredit: " + str(grade.getCredit()) +
                        "\nNumeric Grade: " +str( grade.getNumericGrade()) +
                        "\nLetter Grade: " + grade.getLetterGrade() +
                        "\n--------------------------")
                transcriptInfo.append(temp)
        return transcriptInfo
