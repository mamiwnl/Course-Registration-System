from CourseRegistrationSystem import CourseRegistrationSystem
from logging_config import configure_logging


if __name__ == "__main__":
    configure_logging()
    
    # declare the instance of the registration system
    systemManager = CourseRegistrationSystem()
    
    # fetching data from JSON files and assignment operations
    systemManager.createTranscriptList()
    systemManager.createStudentList()
    systemManager.assignTranscripts()
    
    systemManager.createCourseList()
    systemManager.createLecturerList()
    systemManager.assignLecturerToCourses()
    systemManager.createAdvisor()
    systemManager.assignAdvisorToStudents()
    systemManager.assignPrerequisitesToCourses()
    
    # start the system as login
    systemManager.login()
    
