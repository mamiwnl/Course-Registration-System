import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CourseReader {
    public static ArrayList<Course> readCoursesFromJson(String filePath) {
        ArrayList<Course> courses = new ArrayList<>();

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filePath));
            JSONArray coursesArray = (JSONArray) obj;

            for (Object courseObj : coursesArray) {
                JSONObject courseJson = (JSONObject) courseObj;
                Course course = createCourseFromJson(courseJson);
                if (course != null) {
                    courses.add(course);
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return courses;
    }

    private static Course createCourseFromJson(JSONObject jsonObject) {
        // Extract data from JSON and create a Course object
        String courseName = (String) jsonObject.get("courseName");
        String courseCode = (String) jsonObject.get("courseCode");
        int courseSemester = ((Long) jsonObject.get("courseSemester")).intValue();
        String courseType = (String) jsonObject.get("courseType");
        int credit = ((Long) jsonObject.get("credit")).intValue();
        ArrayList<CourseSection> courseSections = new ArrayList<>();

        // Process courseSections if available
        JSONArray sectionArray = (JSONArray) jsonObject.get("courseSections");
        if (sectionArray != null) {
            for (Object sectionObj : sectionArray) {
                JSONObject sectionJson = (JSONObject) sectionObj;

                String courseSectionCode = (String) sectionJson.get("courseSectionCode");
                String classroom = (String) sectionJson.get("classroom");
                int capacity = ((Long) sectionJson.get("capacity")).intValue();
                int noOfEnrolledStudent = ((Long) sectionJson.get("noOfEnrolledStudent")).intValue();
                String day = (String) sectionJson.get("day");
                String hour = (String) sectionJson.get("hour");
                CourseSection section = new CourseSection(courseName, courseCode, courseSemester, courseType, credit, courseSections, null, null, courseSectionCode, classroom, capacity,noOfEnrolledStudent, day, hour);
                courseSections.add(section);
            }
        }

        // Create and return the Course object
        return new Course(courseName, courseCode, courseSemester, courseType, credit, courseSections, null, null);
    }
}
