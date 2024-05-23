import json
from Lecturer import Lecturer

def readLecturersFromJsonFile(file_path):
    lecturers = []
    try:
        with open(file_path, 'r') as file:
            # Parse the JSON file
            data = json.load(file)

            # Get the array of lecturers
            lecturer_array = data.get("lecturers", [])

            # Iterate through the array and create Lecturer objects
            for lecturer_json in lecturer_array:
                # Extract information from the JSON object
                lecturer_id = int(lecturer_json.get("id", 0))
                password = lecturer_json.get("password", "")
                name = lecturer_json.get("name", "")
                surname = lecturer_json.get("surname", "")
                title = lecturer_json.get("title", "")

                # Create a new Lecturer object and add it to the list
                lecturer = Lecturer(lecturer_id, password, name, surname, title)
                lecturers.append(lecturer)

    except (IOError, json.JSONDecodeError) as e:
        print(f"Error: {e}")
    return lecturers
