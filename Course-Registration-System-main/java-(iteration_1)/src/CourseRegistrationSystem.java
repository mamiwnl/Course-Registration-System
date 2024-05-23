import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CourseRegistrationSystem {
    private ArrayList<Transcript> transcripts;
    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    private ArrayList<Lecturer> lecturers;
    private ArrayList<Advisor> advisors;

    public CourseRegistrationSystem() {
        this.transcripts = new ArrayList<>();
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.lecturers = new ArrayList<>();
        this.advisors = new ArrayList<>();
    }

    // creates transcripts
    public void createTranscriptList() {
        String directoryPath = "Data/Transcripts";

        try {
            // List all files in the directory
            List<java.nio.file.Path> filePaths = Files.list(Paths.get(directoryPath)).collect(Collectors.toList());

            // Iterate through each file and read the transcript
            for (Path filePath : filePaths) {
                TranscriptReader transcriptReader = new TranscriptReader();
                Transcript transcript = transcriptReader.readTranscriptFromFile(filePath.toString());
                getTranscripts().add(transcript);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

    }

    // creates students
    public void createStudentList() {
        String filePath = "Data/student.json";
        students = (ArrayList<Student>) StudentReader.readStudentsFromFile(filePath);
    }

    // assigns transcripts to students
    public void assignTranscripts() {
        for (int i = 0; i < students.size(); i++) {
            for (int j = 0; j < transcripts.size(); j++) {
                if (transcripts.get(j).getId() == students.get(i).getId()) {
                    students.get(i).setTranscript(transcripts.get(j));
                }
            }
        }
    }

    // creates courses
    public void createCourseList() {
        String coursePath = "Data/course.json";
        courses = CourseReader.readCoursesFromJson(coursePath);
    }

    // creates lecturers
    public void createLecturerList() {
        String filePath = "Data/lecturer.json";
        lecturers = LecturerReader.readLecturersFromJsonFile(filePath);
    }

    // assigns lecturers to courses
    public void assignLecturerToCourses() {
        getCourses().get(0).setLecturer(getLecturers().get(0));
        getCourses().get(1).setLecturer(getLecturers().get(1));
        getCourses().get(2).setLecturer(getLecturers().get(2));
        getCourses().get(3).setLecturer(getLecturers().get(3));
        getCourses().get(4).setLecturer(getLecturers().get(4));
        getCourses().get(5).setLecturer(getLecturers().get(5));
        getCourses().get(6).setLecturer(getLecturers().get(6));
        getCourses().get(7).setLecturer(getLecturers().get(7));
        getCourses().get(8).setLecturer(getLecturers().get(8));
        getCourses().get(9).setLecturer(getLecturers().get(9));
        getCourses().get(10).setLecturer(getLecturers().get(10));
        getCourses().get(11).setLecturer(getLecturers().get(1));
        getCourses().get(12).setLecturer(getLecturers().get(11));
        getCourses().get(13).setLecturer(getLecturers().get(8));
        getCourses().get(14).setLecturer(getLecturers().get(9));
        getCourses().get(15).setLecturer(getLecturers().get(5));
        getCourses().get(16).setLecturer(getLecturers().get(10));

    }

    // assigns prerequisites to courses
    public void assignPrerequsitiesToCourses() {
        getCourses().get(11).getPrerequisite().add(getCourses().get(0)); // Operating System
        getCourses().get(10).getPrerequisite().add(getCourses().get(0)); // Database
        getCourses().get(14).getPrerequisite().add(getCourses().get(9)); // Modelling
        getCourses().get(16).getPrerequisite().add(getCourses().get(10)); // Advanced Unix
        getCourses().get(18).getPrerequisite().add(getCourses().get(3)); // Financial Engineering
    }

    // creates advisors from lecturers
    public void createAdvisor() {
        getAdvisors().add(new Advisor(lecturers.get(0)));
        getAdvisors().add(new Advisor(lecturers.get(1)));
        getAdvisors().add(new Advisor(lecturers.get(2)));
        getAdvisors().add(new Advisor(lecturers.get(3)));
        getAdvisors().add(new Advisor(lecturers.get(4)));

    }

    // assigns advisors to students
    public void assignAdvisorToStudents() {
        for (int i = 0; i < students.size(); i++) {
            for (int j = 0; j < advisors.size(); j++) {
                if (students.get(i).getAdvisorID() == advisors.get(j).getId()) {
                    students.get(i).setAdvisor(advisors.get(j));
                    advisors.get(j).getSupervisedStudentList().add(students.get(i));
                }
            }
        }
    }

    // checks the user type and logins the system as student or advisor
    public void login() {
        Scanner input = new Scanner(System.in);
        String selection;

        while (true) {
            System.out.println("----- Welcome to Marmara University Course Registration System -----");
            System.out.println("Type [1] to log in as advisor");
            System.out.println("Type [2] to log in as student");
            System.out.println("Type [q] to quit");
            System.out.print("-> ");
            selection = input.nextLine();

            if (!selection.equals("1") && !selection.equals("2") && !selection.equals("q")) {
                continue;
            }

            if (selection.equals("q")) {
                System.exit(0);
            } else if (selection.equals("1")) {
                advisorAuthentication();
            } else if (selection.equals("2")) {
                studentAuthentication();
            }
        }

    }

    // checks students id and password for authentication
    public void studentAuthentication() {
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        int id;
        String password;
        boolean loginStatus = false;
        int currentStudentIndex = 0;

        do {
            System.out.print("Please enter your id: ");
            id = input.nextInt();

            System.out.print("Please enter your password: ");
            password = input2.nextLine();

            for (int i = 0; i < students.size(); i++) {
                if (id == students.get(i).getId() && password.equals(students.get(i).getPassword())) {
                    currentStudentIndex = i;
                    loginStatus = true;
                    break;
                }
            }
            // if autentication is successfully enter the system as student
            if (loginStatus == true) {
                System.out.println("\nAuthentication successful\n");
                StudentController controller = new StudentController(students.get(currentStudentIndex), courses);
                controller.showOptions();

            } else {
                System.out.println("\nincorrect user ID or password\n");
            }
        } while (loginStatus == false);
    }

    // Asks user to what action he/she wants to do

    // checks advisors id and password for authentication
    public void advisorAuthentication() {
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        int id;
        String password;
        boolean loginStatus = false;
        int currentAdvisorIndex = 0;

        do {
            System.out.println("Please enter your id: ");
            id = input.nextInt();
            System.out.println("Please enter your password: ");
            password = input2.nextLine();

            for (int i = 0; i < advisors.size(); i++) {
                if (id == advisors.get(i).getId() && password.equals(advisors.get(i).getPassword())) {
                    loginStatus = true;
                    currentAdvisorIndex = i;
                    break;
                }
            }
            // if authentication is successfully enter the system as advisor
            if (loginStatus == true) {
                System.out.println("Authentication successful");
                AdvisorController advisorController = new AdvisorController(advisors.get(currentAdvisorIndex), courses);
                advisorController.showOptions();
            } else {
                System.out.println("Authentication failed!");
            }
        } while (loginStatus == false);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Transcript> getTranscripts() {
        return transcripts;
    }

    public void setTranscripts(ArrayList<Transcript> transcripts) {
        this.transcripts = transcripts;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(ArrayList<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public ArrayList<Advisor> getAdvisors() {
        return advisors;
    }

    public void setAdvisors(ArrayList<Advisor> advisors) {
        this.advisors = advisors;
    }
}
