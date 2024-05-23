import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegistrationControl {
    private ArrayList<Course> courses;

    public RegistrationControl(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public ArrayList<Course> getAvailableCourses(Transcript transcript, int semester) {
        ArrayList<Course> availableCourses = null;

        for (int i = 0; i < courses.size(); i++) {
            if (checkSemester(courses.get(i).getCourseSemester(), semester) <= 0) {
                if (!hasPassedCourse(courses.get(i), transcript)) {
                    if (isHasPrequisite(courses.get(i))) {
                        Course eligibleCourse = checkPrerequisite(i, transcript);
                        if (availableCourses == null) {
                            availableCourses = new ArrayList<>();
                        }
                        if (eligibleCourse != null)
                            availableCourses.add(eligibleCourse);
                    } else
                        availableCourses.add(courses.get(i));
                }
            } else if (checkSemester(courses.get(i).getCourseSemester(), semester) > 0) {
                if ((calculateGPA(transcript) >= 3.0)) {
                    if (isHasPrequisite(courses.get(i))) {
                        Course eligibleCourse = checkPrerequisite(i, transcript);
                        if (availableCourses == null) {
                            availableCourses = new ArrayList<>();
                        }
                        if (eligibleCourse != null)
                            availableCourses.add(eligibleCourse);
                    } else
                        availableCourses.add(courses.get(i));
                }
            }
        }
        return availableCourses;
    }

    public int checkSemester(int courseSemester, int semester) {
        return courseSemester - semester;
    }

    public boolean isHasPrequisite(Course course) {
        if (course.getPrerequisite() != null)
            return true;
        else
            return false;
    }

    public Course checkPrerequisite(int courseIndex, Transcript transcript) {
        boolean prerequisitesMet = true;

        for (Course prerequisite : courses.get(courseIndex).getPrerequisite()) {
            boolean hasPassed = false;

            for (Grade grade : transcript.getGrades()) {
                if (prerequisite.getCourseCode().equals(grade.getCourseCode()) &&
                        !grade.getLetterGrade().equals("FF") &&
                        !grade.getLetterGrade().equals("FD") &&
                        !grade.getLetterGrade().equals("DZ") &&
                        !grade.getLetterGrade().equals("FG")) {
                    hasPassed = true;
                    break;
                }
            }

            if (!hasPassed) {
                prerequisitesMet = false;
                break;
            }
        }

        if (prerequisitesMet) {
            return courses.get(courseIndex);
        } else {
            return null; // or handle the case where prerequisites are not met
        }
    }

    public boolean hasPassedCourse(Course course, Transcript transcript) {
        boolean hasPassed = false;

        for (Grade grade : transcript.getGrades()) {
            if (course.getCourseCode().equals(grade.getCourseCode()) &&
                    !grade.getLetterGrade().equals("FF") &&
                    !grade.getLetterGrade().equals("FD") &&
                    !grade.getLetterGrade().equals("DZ") &&
                    !grade.getLetterGrade().equals("FG")) {
                hasPassed = true;
                break;
            }
        }

        if (!hasPassed) {
            return false;
        }
        // All prerequisites are passed, return true
        return true;
    }

    boolean isCourseSectionCodeValid(ArrayList<Course> availableCourses, String selectedCourse) {
        boolean isValid = false;
        for (int i = 0; i < availableCourses.size(); i++) {
            for (int j = 0; j < availableCourses.get(i).getCourseSections().size(); j++) {
                if (availableCourses.get(i).getCourseSections().get(j).getCourseSectionCode().equals(selectedCourse)) {
                    isValid = true;
                }

            }
        }
        return isValid;
    }

    public CourseSection getCourseSection(ArrayList<Course> courses, String selectedCourseSections) {
        CourseSection courseSections = null;
        for (int i = 0; i < courses.size(); i++) {
            for (int j = 0; j < courses.get(i).getCourseSections().size(); j++) {
                if (courses.get(i).getCourseSections().get(j).getCourseSectionCode().equals(selectedCourseSections)) {
                    courseSections = (courses.get(i).getCourseSections().get(j));
                }
            }
        }

        return courseSections;
    }

    public Map<Integer, String> getStudentRegistrationStatus(ArrayList<Student> supervisedStudentList) {
        Map<Integer, String> registrationStatusMap = new HashMap<>(); // (StudentID, registrationMessage)

        int numberOfStudents = supervisedStudentList.size();
        for (int i = 0; i < numberOfStudents; i++) {
            Student student = supervisedStudentList.get(i);
            String registrationStatus = "finalized";

            /*
             * if student has at least one waiting course request, set registration status
             * to waiting
             */
            if (student.getCourseRequests() != null) {
                int numberOfCourseRequests = student.getCourseRequests().size();
                for (int j = 0; j < numberOfCourseRequests; j++) {
                    CourseRequest courseRequest = student.getCourseRequests().get(j);
                    if (courseRequest.getStatus().toLowerCase() == "waiting") {
                        registrationStatus = "waiting";
                        break;
                    }
                }

                String registrationStatusMessage = "" + (i + 1) + " : " + student.getId() + " - " + student.getName()
                        + " " + student.getSurname() + "Registration: "
                        + (registrationStatus == "waiting" ? "waiting for approval" : "finalized");

                registrationStatusMap.put(student.getId(), registrationStatusMessage);
            }
        }

        return registrationStatusMap;
    }

    boolean isHasCapacity(CourseSection courseSection) {
        if (courseSection.getCapacity() < courseSection.getNoOfEnrolledStudent())
            return false;
        else
            return true;
    }

    boolean isOverlapping(ArrayList<CourseSection> currentCourses, CourseSection section) {
        boolean isOverlapping = false;
        if (currentCourses != null) {
            for (int i = 0; i < currentCourses.size(); i++) {
                if ((currentCourses.get(i).getDay().equals(section.getDay()))
                        && (currentCourses.get(i).getHour().equals(section.getHour()))) {
                    isOverlapping = true;
                }

            }
        }
        return isOverlapping;

    }

    // Check if student requests the course section
    public boolean isStudentRequestingCourseSection(Student student, String courseSectionCode) {
        for (int i = 0; i < student.getCourseRequests().size(); i++) {
            if (student.getCourseRequests().get(i).getCourseSection().getCourseSectionCode()
                    .equals(courseSectionCode)) {
                return true;
            }
        }
        return false;
    }

    // Calculate student's current credits taken
    public double currentTotalCredits(ArrayList<CourseSection> currentCourses) {
            double totalCredits=0;
            for(int i=0; i < currentCourses.size(); i++) {
                if(currentCourses.get(i) != null) {
                    totalCredits+=currentCourses.get(i).getCredit();
                }
            }
            return totalCredits;
    }
    public double calculateGPA(Transcript transcript) {
        double impactValue = 0.0;
        double totalCredit=0.0;
        double gpa = 0.0;
        for(int i=0; i<transcript.getGrades().size(); i++) {
            if(transcript.getGrades().get(i).getLetterGrade().equals("FF")) {
                impactValue += (transcript.getGrades().get(i).getCredit()) * 0.0; }
            else if(transcript.getGrades().get(i).getLetterGrade().equals("DD")) {
                impactValue += (transcript.getGrades().get(i).getCredit()) * 1.0; }
            else if(transcript.getGrades().get(i).getLetterGrade().equals("DC")) {
                impactValue+= (transcript.getGrades().get(i).getCredit()) * 1.5; }
            else if(transcript.getGrades().get(i).getLetterGrade().equals("CC")) {
                impactValue+= (transcript.getGrades().get(i).getCredit()) * 2.0; }
            else if(transcript.getGrades().get(i).getLetterGrade().equals("CB")) {
                impactValue+= (transcript.getGrades().get(i).getCredit()) * 2.5; }
            else if(transcript.getGrades().get(i).getLetterGrade().equals("BB")) {
                impactValue+= (transcript.getGrades().get(i).getCredit()) * 3.0; }
            else if(transcript.getGrades().get(i).getLetterGrade().equals("BA")) {
                impactValue+= (transcript.getGrades().get(i).getCredit()) * 3.5; }
            else if(transcript.getGrades().get(i).getLetterGrade().equals("AA")) {
                impactValue+= (transcript.getGrades().get(i).getCredit()) * 4.0; }

            totalCredit+=transcript.getGrades().get(i).getCredit();
        }
        gpa = impactValue/totalCredit;
        gpa = Math.round(gpa*100.0)/100.0;
        transcript.setGPA(gpa);
        return gpa;

    }


}
