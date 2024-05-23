import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class AdvisorController extends UserController {
    private Advisor advisor;
    private RegistrationControl registrationControl;

    public AdvisorController(Advisor advisor, ArrayList<Course> courses) {
        this.advisor = advisor;
        this.registrationControl = new RegistrationControl(courses);
    }

    // Prompt the options when user logs in
    @Override
    public void showOptions() {
        Scanner input = new Scanner(System.in);

        while (true) {
            String reply = "";
            System.out.println("Type [1] Approve/Reject course requests");
            System.out.println("Type [2] List supervised students");
            System.out.println("Type [3] View Info");
            System.out.println("Type [q] to quit");
            System.out.print("-> ");

            try {
                input.hasNextLine();
                reply = input.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }

            // quit
            if (reply.equals("q")) {
                break;
            }

            // evaluate reply
            switch (reply) {
                case "1": // Approve/Reject course requests
                    startRegistrationApprovalProcess();
                    break;

                case "2": // List supervised students
                    listSupervisedStudents();
                    break;

                case "3": // View Info
                    System.out.println(advisor.getInfo());
                    break;

                default:
                    System.out.println("Invalid response: " + reply);
                    break;
            }
        }
    } // showOptions() ends here

    // Operations for advisor approval
    private void startRegistrationApprovalProcess() {
        Scanner input = new Scanner(System.in);
        String reply = ""; // holds what user typed

        while (true) {
            // display student name with registration status
            displayStudentRegistrations();

            System.out.println("To view student's registration, enter the student ID.");
            System.out.println("Press [q] to quit.");
            System.out.print("-> ");
            try {
                reply = input.nextLine();

            } catch (Exception e) {
                System.out.println("Invalid student ID: " + reply);
                continue;
            }

            // quit
            if (reply.equals("q")) {
                break;

            }

            // check if the given student ID is valid
            if (advisor.isSupervisingStudent(reply)) {
                Student student = advisor.getSupervisedStudent(reply);
                System.out.println();
                System.out.println(student.getInfo());

                displayRegisteredCourses(student);

                while (true) {
                    System.out.print("Enter course code to change status or [q] to quit) \n-> ");
                    reply = input.nextLine();

                    // quit
                    if (reply.equals("q"))
                        break;

                    // check if student requested the course
                    if (registrationControl.isStudentRequestingCourseSection(student, reply)) {
                        CourseRequest courseRequest = null;
                        CourseSection sectionRequest = null;
                        for (int i = 0; i < student.getCourseRequests().size(); i++) {
                            if (student.getCourseRequests().get(i).getCourseSection().getCourseSectionCode().equals(reply)) {
                                courseRequest = student.getCourseRequests().get(i);
                                sectionRequest = student.getCourseRequests().get(i).getCourseSection();
                            }
                        }

                        while (true) {
                            System.out.println(
                                    "Change status to [a] approved or [r] rejected. Enter [q] to quit. \n-> ");
                            reply = input.nextLine();

                            if (reply.equalsIgnoreCase("q"))
                                break;

                            if (reply.equalsIgnoreCase("a")) {
                                System.out.println("Request successfully approved");
                                courseRequest.setStatus("approved");
                                ArrayList<CourseSection> temp = student.getCurrentCourses();
                                if (temp == null) {
                                    temp = new ArrayList<>();
                                }
                                temp.add(sectionRequest);
                                student.setCurrentCourses(temp);
                                Grade grade =new Grade(sectionRequest.getCourseName(), sectionRequest.getCourseCode(),sectionRequest.getCourseSemester(),sectionRequest.getCredit(),-1,"");
                                student.getTranscript().getGrades().add(grade);
                                TranscriptSaver transcriptSaver = new TranscriptSaver();
                                transcriptSaver.save(student.getTranscript());
                                break;
                            }

                            else if (reply.equalsIgnoreCase("r")) {
                                System.out.println("Request successfully rejected");
                                courseRequest.setStatus("rejected");
                            }

                            else {
                                System.out.println("Invalid request.");
                            }

                        }
                    }

                    // invalid course code warning
                    else {
                        System.out.println("Invalid course code");
                    }
                }
            }

            // invalid student ID warning
            else {
                System.out.println("Invalid student ID: " + reply);
                continue;
            }

        }

    }

    private void displayStudentRegistrations() {
        Map<Integer, String> registrationStatusMap = registrationControl
                .getStudentRegistrationStatus(advisor.getSupervisedStudentList());

        for (int i = 0; i < advisor.getSupervisedStudentList().size(); i++) {
            int studentID = advisor.getSupervisedStudentList().get(i).getId();

            // for each student print the registration status if s/he has any course
            // requests
            if (registrationStatusMap.containsKey(studentID)) {
                System.out.println(registrationStatusMap.get(studentID));
            }
        }
    }

    private void displayRegisteredCourses(Student student) {
        for (int i = 0; i < student.getCourseRequests().size(); i++) {
            System.out.println("\t" + student.getCourseRequests().get(i).getCourseSection().getCourseName() +
                    " " + student.getCourseRequests().get(i).getCourseSection().getCourseSectionCode()
                    + ": " + student.getCourseRequests().get(i).getStatus());
        }
    }

    private void listSupervisedStudents() {
        for (int i = 0; i < advisor.getSupervisedStudentList().size(); i++) {
            System.out.println(advisor.getSupervisedStudentList().get(i).getInfo());
        }
    }

}
