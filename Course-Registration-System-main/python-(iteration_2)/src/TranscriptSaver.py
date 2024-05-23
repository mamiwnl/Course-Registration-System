import json
import logging

class TranscriptSaver:
    def __init__(self):
        self.__file_path = ""
        self.logger = logging.getLogger(__name__)
        logging.basicConfig(level=logging.INFO)
        
    def save(self, transcript):
        # Define the file path for the transcript based on its ID
        self.__file_path = f"../Data/Transcripts/transcript{transcript.getId()}.json"
        try:
            with open(self.__file_path, 'w') as file_writer:
                json_transcript = {
                    "id": transcript.getId(),
                    "GPA": transcript.getGPA(),
                    "grades": []
                }
                self.logger.info("Transcript is changed for student with ID: %d", transcript.getId())

                # Check if there are grades in the transcript
                if transcript.getGrades() and len(transcript.getGrades()) > 0:
                    for grade in transcript.getGrades():
                        grade_data = {
                            "courseName": grade.getCourseName(),
                            "courseCode": grade.getCourseCode(),
                            "semester": grade.getSemester(),
                            "credit": grade.getCredit(),
                            "numericGrade": grade.getNumericGrade(),
                            "letterGrade": grade.getLetterGrade()
                        }
                        json_transcript["grades"].append(grade_data)
                
                # Write the constructed JSON transcript to the file
                file_writer.write(json.dumps(json_transcript, indent=2))
        except IOError as e:
            # If an exception occurs during file writing, display an error message
            print(f"Failed to save transcript to file: {e}")

    def getFilePath(self):
        return self.__file_path
