class RegistrationControl:
    def __init__(self, courses):
        self.__courses = courses

    def getAvailableCourses(self, transcript, semester):
        availableCourses = []

        for course in self.__courses:
            if self.checkSemester(course.getCourseSemester(), semester) <= 0:
                if not self.hasPassedCourse(course, transcript):
                    if self.isHasPrerequisite(course):
                        eligibleCourse = self.checkPrerequisite(course, transcript)
                        if eligibleCourse is not None:
                            availableCourses.append(eligibleCourse)
                    else:
                        availableCourses.append(course)
            elif self.checkSemester(course.getCourseSemester(), semester) > 0:
                if self.calculateGPA(transcript) >= 3.0:
                    if self.isHasPrerequisite(course):
                        eligibleCourse = self.checkPrerequisite(course, transcript)
                        if eligibleCourse is not None:
                            availableCourses.append(eligibleCourse)
                    else:
                        availableCourses.append(course)

        return availableCourses

    def checkSemester(self, courseSemester, semester):
        return courseSemester - semester

    def isHasPrerequisite(self, course):
        return course.getPrerequisite() is not None

    def checkPrerequisite(self, course, transcript):
        prerequisitesMet = True

        for prerequisite in course.getPrerequisite():
            hasPassed = any(
                grade.getCourseCode() == prerequisite.getCourseCode() and
                grade.getLetterGrade() not in ["FF", "FD", "DZ", "FG"]
                for grade in transcript.getGrades())

            if not hasPassed:
                prerequisitesMet = False
                break

        return course if prerequisitesMet else None

    def hasPassedCourse(self, course, transcript):
        return any(
            grade.getCourseCode() == course.getCourseCode() and
            grade.getLetterGrade() not in ["FF", "FD", "DZ", "FG"]
            for grade in transcript.getGrades())


    def isCourseSectionCodeValid(self, availableCourses, selectedCourse):
        return any(
            any(section.getCourseSectionCode() == selectedCourse for section in course.getCourseSections())
            for course in availableCourses)

    def getCourseSection(self, courses, selectedCourseSections):
        for course in courses:
            for section in course.getCourseSections():
                if section.getCourseSectionCode() == selectedCourseSections:
                    return section
        return None

    def getStudentRegistrationStatus(self, supervisedStudentList):
        registrationStatusMap = {}

        for i, student in enumerate(supervisedStudentList):
            registrationStatus = "finalized"

            if student.getCourseRequests():
                for courseRequest in student.getCourseRequests():
                    if courseRequest.status.lower() == "waiting":
                        registrationStatus = "waiting"
                        break

                registrationMessage = f"{i + 1} : {student.getId()} - {student.getName()} {student.getSurname()} Registration: {'waiting for approval' if registrationStatus == 'waiting' else 'finalized'}"
                registrationStatusMap[student.getId()] = registrationMessage

        return registrationStatusMap

    def hasCapacity(self, courseSection):
        return courseSection.getCapacity() > courseSection.getNoOfEnrolledStudents()

    def isOverlapping(self, currentCourses, section):
        return any(
            currentCourse.day == section.day and currentCourse.hour == section.hour
            for currentCourse in currentCourses)

    def isStudentRequestingCourseSection(self, student, courseSectionCode):
        return any(
            request.getCourseSection().getCourseSectionCode() == courseSectionCode
            for request in student.getCourseRequests())

    def currentTotalCredits(self, currentCourses):
        return sum(section.credit for section in currentCourses if section is not None)

    def calculateGPA(self, transcript):
        impactValue = 0.0
        totalCredit = 0.0

        for grade in transcript.getGrades():
            letterGrade = grade.getLetterGrade()
            credit = grade.getCredit()
            gradeValue = {"FF": 0.0, "DD": 1.0, "DC": 1.5, "CC": 2.0, "CB": 2.5, "BB": 3.0, "BA": 3.5, "AA": 4.0}.get(letterGrade, 0.0)
            impactValue += credit * gradeValue
            totalCredit += credit

        GPA = impactValue / totalCredit if totalCredit > 0 else 0.0
        return round(GPA, 2)

