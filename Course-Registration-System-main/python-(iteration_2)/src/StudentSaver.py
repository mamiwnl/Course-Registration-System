import json

class StudentSaver:
    def __init__(self):
        self.__currentCourses = []
        self.__requests = []

    def save(self, studentId, updatedCourses, updatedRequests):
        try:
            with open("Data/student.json", 'r') as file:
                studentList = json.load(file)

                for student in studentList:
                    if student['id'] == studentId:
                        student['currentCourses'] = self.__getJsonCourses(updatedCourses)
                        student['requests'] = self.__getJsonRequests(updatedRequests)

                with open("Data/student.json", 'w') as file:
                    json.dump(studentList, file, indent=2)

        except (IOError, json.JSONDecodeError) as e:
            print(f"Error occurred: {e}")

    def getJsonCourses(self, courses):
        coursesArray = []
        for course in courses:
            coursesArray.append(course.toJson())
        return coursesArray

    def getJsonRequests(self, requests):
        requestsArray = []
        for request in requests:
            requestsArray.append(request.toJson())
        return requestsArray

    def __getJsonCourses(self, courses):
        coursesArray = []
        for course in courses:
            coursesArray.append(course.toJson())
        return coursesArray

    def __getJsonRequests(self, requests):
        requestsArray = []
        for request in requests:
            requestsArray.append(request.toJson())
        return requestsArray

    def setCurrentCourses(self, courses):
        self.__currentCourses = courses

    def setRequests(self, requests):
        self.__requests = requests

    def getCurrentCourses(self):
        return self.__currentCourses

    def getRequests(self):
        return self.__requests
