import json
from Grade import Grade
from Transcript import Transcript

def readTranscriptFromFile(filePath):
    try:
        with open(filePath, 'r') as file:
            # Load JSON data from the file
            jsonData = json.load(file)

            # Extract data from JSON
            transcript = Transcript(
                int(jsonData['id']),
                float(jsonData['GPA']),
                []
            )

            # Process grades
            gradesData = jsonData['grades']
            for gradeData in gradesData:
                grade = Grade(
                    gradeData['courseName'],
                    gradeData['courseCode'],
                    int(gradeData['semester']),
                    float(gradeData['credit']),
                    float(gradeData['numericGrade']),
                    gradeData['letterGrade']
                )
                transcript.getGrades().append(grade)

            return transcript

    except (IOError, json.JSONDecodeError) as e:
        print(f"Error: {e}")
        return None
