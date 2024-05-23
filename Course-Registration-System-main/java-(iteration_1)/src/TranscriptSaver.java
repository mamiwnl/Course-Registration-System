import java.io.FileWriter;
import java.io.IOException;

public class TranscriptSaver {

    // Method to save a transcript object to a JSON file
    public void save(Transcript transcript) {
        // Define the file path for the transcript based on its ID
        String filePath = "Data/Transcripts/transcript" + transcript.getId() + ".json";
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            StringBuilder jsonTranscript = new StringBuilder();
            
            // Build the JSON structure for the transcript
            jsonTranscript.append("{\n")
                    .append("  \"id\": ").append(transcript.getId()).append(",\n")
                    .append("  \"GPA\": ").append(transcript.getGPA()).append(",\n");

            // Check if there are grades in the transcript
            if (transcript.getGrades() != null && !transcript.getGrades().isEmpty()) {
                jsonTranscript.append("  \"grades\": [\n");
                
                // Iterate through each grade and add it to the JSON structure
                for (int i = 0; i < transcript.getGrades().size(); i++) {
                    Grade grade = transcript.getGrades().get(i);
                    jsonTranscript.append("    {\n")
                            .append("      \"courseName\": \"").append(grade.getCourseName()).append("\",\n")
                            .append("      \"courseCode\": \"").append(grade.getCourseCode()).append("\",\n")
                            .append("      \"semester\": \"").append(grade.getSemester()).append("\",\n")
                            .append("      \"credit\": ").append(grade.getCredit()).append(",\n")
                            .append("      \"numericGrade\": ").append(grade.getNumericGrade()).append(",\n")
                            .append("      \"letterGrade\": \"").append(grade.getLetterGrade()).append("\"\n")
                            .append("    }");

                    // Add a comma if there are more grades to be added, otherwise end the JSON array
                    if (i < transcript.getGrades().size() - 1) {
                        jsonTranscript.append(",\n");
                    } else {
                        jsonTranscript.append("\n");
                    }
                }
                jsonTranscript.append("  ]\n");
            } else {
                // If there are no grades, add an empty grades array to the JSON structure
                jsonTranscript.append("  \"grades\": []\n");
            }

            jsonTranscript.append("}");

            // Write the constructed JSON transcript to the file
            fileWriter.write(jsonTranscript.toString());
        } catch (IOException e) {
            // If an exception occurs during file writing, display an error message
            System.err.println("Failed to save transcript to file: " + e.getMessage());
            // logger.error("Failed to save transcript to file", e); - This might be a log statement for logging the error.
        }
    }
}
