import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StudentReader {

    // Reads the student's info from the json file
    public static ArrayList<Student> readStudentsFromFile(String filePath) {
        JSONParser parser = new JSONParser();
        ArrayList<Student> studentList = new ArrayList<>();

        try {
            // Parse the JSON file
            Object obj = parser.parse(new FileReader(filePath));

            // Check if the parsed object is a JSON array
            if (obj instanceof JSONArray) {
                // Convert the parsed object to a JSONArray
                JSONArray jsonArray = (JSONArray) obj;

                // Iterate over each student in the array
                for (Object studentObj : jsonArray) {
                    // Convert the student object to a JSONObject
                    JSONObject studentJson = (JSONObject) studentObj;

                    // Extract values from the JSON object
                    int id = parseIntOrNull(studentJson, "id");
                    String password = (String) studentJson.get("password");
                    String name = (String) studentJson.get("name");
                    String surname = (String) studentJson.get("surname");
                    int semester = parseIntOrNull(studentJson, "semester");
                    String major = (String) studentJson.get("major");
                    int advisorID = parseIntOrNull(studentJson, "advisorID");

                    // Extract currentCourses information from the JSON object
                    JSONArray currentCoursesJsonArray = (JSONArray) studentJson.get("currentCourses");
                    ArrayList<CourseSection> currentCourses = new ArrayList<>();

                    if (currentCoursesJsonArray != null) {
                        for (Object courseObj : currentCoursesJsonArray) {
                            JSONObject courseJson = (JSONObject) courseObj;

                            // Extract values from the JSON object for CourseSection
                            String courseName = (String) courseJson.get("courseName");
                            String courseCode = (String) courseJson.get("courseCode");
                            int courseSemester = parseIntOrNull(courseJson, "courseSemester");
                            String courseType = (String) courseJson.get("courseType");
                            int credit = parseIntOrNull(courseJson, "credit");
                            String courseSectionCode = (String) courseJson.get("courseSectionCode");
                            String classroom = (String) courseJson.get("classroom");
                            int capacity = parseIntOrNull(courseJson, "capacity");
                            int noOfEnrolledStudent = parseIntOrNull(courseJson, "noOfEnrolledStudent");
                            String day = (String) courseJson.get("day");
                            String hour = (String) courseJson.get("hour");


                            // Create empty lists if needed
                            ArrayList<CourseSection> courseSections = new ArrayList<>();
                            ArrayList<Course> prerequisite = new ArrayList<>();
                            Lecturer lecturer = null; // or initialize with a lecturer if available

                            // Create CourseSection object and add to the list
                            CourseSection courseSection = new CourseSection(
                                    courseName, courseCode, courseSemester, courseType, credit,
                                    courseSections, prerequisite, lecturer,
                                    courseSectionCode, classroom, capacity, noOfEnrolledStudent, day, hour
                            );
                            currentCourses.add(courseSection);
                        }
                    }


                    // Extract requests information from the JSON object
                    JSONArray requestsJsonArray = (JSONArray) studentJson.get("requests");
                    ArrayList<CourseRequest> requests = null;

                    if (requestsJsonArray != null) {
                        requests = new ArrayList<>();
                        for (Object requestObj : requestsJsonArray) {
                            JSONObject requestJson = (JSONObject) requestObj;

                            // Extract values from the JSON object for CourseRequest
                            int studentID = parseIntOrNull(requestJson, "studentID");
                            // Assuming there is a nested object for CourseSection within CourseRequest
                            JSONObject courseSectionJson = (JSONObject) requestJson.get("courseSection");
                            String courseName = (String) courseSectionJson.get("courseName");
                            String courseCode = (String) courseSectionJson.get("courseCode");
                            int courseSemester = parseIntOrNull(courseSectionJson, "courseSemester");
                            String courseType = (String) courseSectionJson.get("courseType");
                            int credit = parseIntOrNull(courseSectionJson, "credit");
                            String courseSectionCode = (String) courseSectionJson.get("courseSectionCode");
                            String classroom = (String) courseSectionJson.get("classroom");
                            int capacity = parseIntOrNull(courseSectionJson, "capacity");
                            int noOfEnrolledStudent = parseIntOrNull(courseSectionJson, "noOfEnrolledStudent");
                            String day = (String) courseSectionJson.get("day");
                            String hour = (String) courseSectionJson.get("hour");
                            String status = (String) requestJson.get("status");


                            // Create CourseSection object
                            CourseSection courseSection = new CourseSection(
                                    courseName, courseCode, courseSemester, courseType, credit, null, null, null,
                                    courseSectionCode, classroom, capacity, noOfEnrolledStudent, day, hour
                            );

                            // Create CourseRequest object and add to the list
                            CourseRequest courseRequest = new CourseRequest(studentID, courseSection,status);
                            requests.add(courseRequest);
                        }
                    }


                    // Create a Student object using values from the JSON
                    Student student = new Student(id, password, name, surname, semester, major, advisorID, null, currentCourses, null, requests);

                    // Add the student to the list
                    studentList.add(student);
                }
            } else {
                System.err.println("Invalid JSON format. Expected an array.");
            }

            return studentList;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Integer parseIntOrNull(JSONObject jsonObject, String key) {
        Object value = jsonObject.get(key);
        return value != null ? Integer.parseInt(value.toString()) : null;
    }
}
