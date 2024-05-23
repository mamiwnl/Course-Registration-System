import java.util.ArrayList;
import java.util.Scanner;

public class StudentController {
    private ArrayList<Course> courses;
    Student student;

    public StudentController(Student student, ArrayList<Course> courses) {
        this.student = student;
        this.courses = courses;
    }

    public String[] takeCoursesInput() {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        System.out.print("How many courses do you want to enroll?(Please type integer!): ");
        String input = scanner.nextLine();
        String[] courseSectionCodes = new String[10];
        int intValue;
        try {
            // Attempt to parse the input as an integer
            intValue = Integer.parseInt(input);
            // If parsing is successful, it's an integer

        } catch (NumberFormatException e) {
            // If an exception is caught, it's not an integer
            intValue = 0;
            System.out.println("Error: Input is not an integer!");
        }

        for (int i = 0; i < intValue; i++) {
            String code;
            System.out.print("Enter the " + (i + 1) + ". course code with section no (e.g: CSE2225.1): ");
            code = scanner1.nextLine();
            courseSectionCodes[i] = code;
        }

        return courseSectionCodes;
    }

    public static void printCourseSchedule(ArrayList<CourseSection> sections) {
        System.out.println("Course Schedule:");
        System.out.printf("%-15s%-15s%-15s\n",
                "Classroom", "Day", "Hour");
        System.out.println("-------------------------");

        for (CourseSection section : sections) {
            System.out.printf("%-15s%-15s%-15s\n",
                    section.getClassroom(), section.getDay(), section.getHour());
        }
    }

    public void showOptions() {
        Scanner input = new Scanner(System.in);
        String selection;

        do {
            System.out.println("Type [1] Enroll a course");
            System.out.println("Type [2] List enrolled courses");
            System.out.println("Type [3] View Transcript");
            System.out.println("Type [4] View Info");
            System.out.println("Type [5] Requested courses info");
            System.out.println("Type [6] View Time Table");
            System.out.println("Type [q] to quit");
            System.out.print("-> ");
            selection = input.nextLine();

            switch (selection) {
                case "1":

                    RegistrationControl r = new RegistrationControl(courses);
                    ArrayList<Course> kurs = r.getAvailableCourses(student.getTranscript(), student.getSemester());
                    for (int i = 0; i < kurs.size(); i++) {
                        for (int j = 0; j < kurs.get(i).getCourseSections().size(); j++) {
                            System.out.println(kurs.get(i).getCourseSections().get(j).viewCourseSectionInfo());
                        }
                    }
                    String requestedCourseSectionCode[] = takeCoursesInput();
                    CourseSection a = null;
                    for (int j = 0; j < requestedCourseSectionCode.length; j++) {
                        a = null;
                        if (requestedCourseSectionCode[j] != null) {
                            if (r.isCourseSectionCodeValid(kurs, requestedCourseSectionCode[j])) {
                                a = r.getCourseSection(kurs, requestedCourseSectionCode[j]);
                                if ((r.isHasCapacity(a))) {
                                    if (!(r.isOverlapping(student.getCurrentCourses(), a))) {
                                        if (r.currentTotalCredits(student.getCurrentCourses()) < 30) {
                                            CourseRequest request = new CourseRequest(student.getId(), a, "waiting");
                                            System.out.println(a.getCourseSectionCode() + " successfully requested.");
                                            if (student.getCourseRequests() == null) {
                                                ArrayList<CourseRequest> temp = student.getCourseRequests();
                                                temp = new ArrayList<>();
                                                temp.add(request);
                                                student.setCourseRequests(temp);

                                            } else
                                                student.getCourseRequests().add(request);

                                        } else
                                            System.out.println("Failed: Total credits taken should not exceed 30.0!");
                                    } else
                                        System.out.println("Failed: " + a.getCourseSectionCode() + " overlapping!");
                                } else
                                    System.out.println("Failed: " + a.getCourseSectionCode() + " section is full!");
                            } else
                                System.out.println("Failed: Section code is not valid!");
                        }
                    }

                    break;
                case "2":
                    for (int i = 0; i < student.listCurrentCourses().length; i++)
                        if (student.listCurrentCourses()[i] != null) {
                            System.out.println(student.listCurrentCourses()[i]);
                        }
                    break;
                case "3":
                    for (int j = 0; j < student.getTranscript().viewTranscript().length; j++)
                        if (student.getTranscript().viewTranscript()[j] != null) {
                            System.out.println(student.getTranscript().viewTranscript()[j]);
                        }
                    break;
                case "4":
                    System.out.println(student.getInfo());
                    break;
                case "5":
                    if (student.getCourseRequests() != null) {
                        for (int i = 0; i < student.getCourseRequests().size(); i++) {
                            System.out.println(student.getCourseRequests().get(i).viewRequestInfo());
                        }
                    } else {
                        System.out.println("There is no course request");
                    }
                    break;
                case "6":
                    printCourseSchedule(student.getCurrentCourses());
                    break;
                case "q":
                    TranscriptSaver transcriptSaver = new TranscriptSaver();
                    transcriptSaver.save(student.getTranscript());
                    break;
                default:
                    System.out.println("Wrong input! Please enter 1,2,3,4 or q");
            }
        } while (!selection.equals("q"));

    }
}
