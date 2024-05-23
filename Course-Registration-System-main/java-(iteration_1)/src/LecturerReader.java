import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class LecturerReader {
    public static ArrayList<Lecturer> readLecturersFromJsonFile(String filePath) {
        ArrayList<Lecturer> lecturers = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(filePath)) {
            // Parse the JSON file
            Object obj = jsonParser.parse(reader);

            // Cast the parsed object to JSONObject
            JSONObject jsonObject = (JSONObject) obj;

            // Get the array of lecturers
            JSONArray lecturerArray = (JSONArray) jsonObject.get("lecturers");

            // Iterate through the array and create Lecturer objects
            for (Object lecturerObj : lecturerArray) {
                JSONObject lecturerJson = (JSONObject) lecturerObj;

                // Extract information from the JSON object
                int id = ((Long) lecturerJson.get("id")).intValue();
                String password = (String) lecturerJson.get("password");
                String name = (String) lecturerJson.get("name");
                String surname = (String) lecturerJson.get("surname");
                String title = (String) lecturerJson.get("title");

                // Create a new Lecturer object and add it to the list
                Lecturer lecturer = new Lecturer(id, password, name, surname, title);
                lecturers.add(lecturer);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return lecturers;
    }
}
