import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TranscriptReader {
    public Transcript readTranscriptFromFile(String filePath) {
        JSONParser parser = new JSONParser();

        try {
            // Parse the JSON file
            Object obj = parser.parse(new FileReader(filePath));

            // Convert the parsed object to a JSONObject
            JSONObject jsonObject = (JSONObject) obj;

            // Retrieve the 'id' from the JSONObject
            long id = (long) jsonObject.get("id");
            double GPA = (double) jsonObject.get("GPA");

            // Retrieve the 'grades' JSONArray from the JSONObject
            JSONArray jsonArray = (JSONArray) jsonObject.get("grades");

            // Create a list to store Grade objects
            ArrayList<Grade> grades = new ArrayList<>();

            // Iterate over the 'grades' JSONArray and create Grade objects
            for (Object gradeObj : jsonArray) {
                JSONObject gradeJson = (JSONObject) gradeObj;
                String courseName = (String) gradeJson.get("courseName");
                String courseCode = (String) gradeJson.get("courseCode");
                int semester = Integer.parseInt(gradeJson.get("semester").toString());
                double credit = (double) gradeJson.get("credit");
                double numericGrade = (double) gradeJson.get("numericGrade");
                String letterGrade = (String) gradeJson.get("letterGrade");

                Grade grade = new Grade(courseName, courseCode, semester , credit, numericGrade, letterGrade);
                grades.add(grade);
            }

            // Create and return a Transcript object
            Transcript transcript = new Transcript();
            transcript.setId((int) id);
            transcript.setGPA(GPA);
            transcript.setGrades(grades);

            return transcript;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
