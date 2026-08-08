package main;
import java.util.Scanner;

import data.Attendancedata;
import data.Coursedata;
import data.Enrollmentdata;
import data.Marksdata;
import data.Studentdata;
import entity.Course;
import entity.Student;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Studentdata studentdata = new Studentdata();
        Coursedata coursedata = new Coursedata();
        Enrollmentdata enrollmentdata = new Enrollmentdata();
        Attendancedata attendancedata = new Attendancedata();
        Marksdata marksdata = new Marksdata();

        while (true) {
            System.out.println(" STUDENT MANAGEMENT SYSTEM ");
            System.out.println("1. Student Management");
            System.out.println("2. Course Management");
            System.out.println("3. Course Enrollment");
            System.out.println("4. Attendance Management");
            System.out.println("5. Marks Management");
            System.out.println("6. Generate Result");
            System.out.println("7. Exit");
            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();

            switch (choice) {
            case 1:
                System.out.println("\n------ Student Menu ------");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");

                int studentChoice = sc.nextInt();

                switch (studentChoice) {

                case 1:

                    Student s = new Student();

                    sc.nextLine();

                    System.out.print("Student Name : ");
                    s.setStudentName(sc.nextLine());

                    System.out.print("Gender : ");
                    s.setGender(sc.nextLine());

                    System.out.print("Age : ");
                    s.setAge(sc.nextInt());

                    sc.nextLine();

                    System.out.print("Phone : ");
                    s.setPhone(sc.nextLine());

                    System.out.print("Email : ");
                    s.setEmail(sc.nextLine());

                    System.out.print("Department : ");
                    s.setDepartment(sc.nextLine());

                    studentdata.addStudent(s);

                    break;

                case 2:

                    studentdata.viewStudents();
                    break;

                case 3:

                    Student update = new Student();

                    System.out.print("Student ID : ");
                    update.setStudentId(sc.nextInt());

                    sc.nextLine();

                    System.out.print("Student Name : ");
                    update.setStudentName(sc.nextLine());

                    System.out.print("Gender : ");
                    update.setGender(sc.nextLine());

                    System.out.print("Age : ");
                    update.setAge(sc.nextInt());

                    sc.nextLine();

                    System.out.print("Phone : ");
                    update.setPhone(sc.nextLine());

                    System.out.print("Email : ");
                    update.setEmail(sc.nextLine());

                    System.out.print("Department : ");
                    update.setDepartment(sc.nextLine());

                    studentdata.updateStudent(update);

                    break;

                case 4:

                    System.out.print("Student ID : ");
                    int deleteId = sc.nextInt();

                    studentdata.deleteStudent(deleteId);

                    break;

                default:
                    System.out.println("Invalid Choice");
                }

                break;
            case 2:
                System.out.println("\n------ Course Menu ------");
                System.out.println("1. Add Course");
                System.out.println("2. View Courses");
                System.out.println("3. Update Course");
                System.out.println("4. Delete Course");

                int courseChoice = sc.nextInt();

                switch (courseChoice) {

                case 1:

                    Course c = new Course();

                    sc.nextLine();

                    System.out.print("Course Name : ");
                    c.setCourseName(sc.nextLine());

                    System.out.print("Duration : ");
                    c.setDuration(sc.nextLine());

                    System.out.print("Trainer Name : ");
                    c.setTrainerName(sc.nextLine());

                    System.out.print("Fees : ");
                    c.setFees(sc.nextDouble());

                    coursedata.addCourse(c);

                    break;

                case 2:

                    coursedata.viewCourses();

                    break;

                case 3:

                    Course course = new Course();

                    System.out.print("Course ID : ");
                    course.setCourseId(sc.nextInt());

                    sc.nextLine();

                    System.out.print("Course Name : ");
                    course.setCourseName(sc.nextLine());

                    System.out.print("Duration : ");
                    course.setDuration(sc.nextLine());

                    System.out.print("Trainer Name : ");
                    course.setTrainerName(sc.nextLine());

                    System.out.print("Fees : ");
                    course.setFees(sc.nextDouble());

                    coursedata.updateCourse(course);

                    break;

                case 4:

                    System.out.print("Course ID : ");
                    int cid = sc.nextInt();

                    coursedata.deleteCourse(cid);

                    break;

                default:
                    System.out.println("Invalid Choice");
                }

                break;
                case 3:

                System.out.print("Student ID : ");
                int sid = sc.nextInt();

                System.out.print("Course ID : ");
                int coid = sc.nextInt();

                sc.nextLine();

                System.out.print("Enrollment Date (YYYY-MM-DD): ");
                String date = sc.nextLine();

                enrollmentdata.enrollStudent(sid, coid, date);

                System.out.println("\nEnrollment List");

                enrollmentdata.viewEnrollments();

                break;
                case 4:

                System.out.println("\n------ Attendance Menu ------");
                System.out.println("1. Mark Attendance");
                System.out.println("2. View Attendance");
                System.out.println("3. Update Attendance");
                System.out.println("4. Delete Attendance");

                int attendanceChoice = sc.nextInt();

                switch (attendanceChoice) {

                case 1:

                    System.out.print("Student ID : ");
                    sid = sc.nextInt();

                    System.out.print("Course ID : ");
                    coid = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Attendance Date : ");
                    String attendanceDate = sc.nextLine();

                    System.out.print("Status (Present/Absent): ");
                    String status = sc.nextLine();

                    attendancedata.markAttendance(sid, coid, attendanceDate, status);

                    break;

                case 2:

                    attendancedata.viewAttendance();

                    break;

                case 3:

                    System.out.print("Attendance ID : ");
                    int aid = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Status : ");
                    status = sc.nextLine();

                    attendancedata.updateAttendance(aid, status);

                    break;

                case 4:

                    System.out.print("Attendance ID : ");
                    aid = sc.nextInt();

                    attendancedata.deleteAttendance(aid);

                    break;

                default:
                    System.out.println("Invalid Choice");
                }

                break;
                case 5:

                System.out.println("\n------ Marks Menu ------");
                System.out.println("1. Add Marks");
                System.out.println("2. View Marks");
                System.out.println("3. Update Marks");
                System.out.println("4. Delete Marks");

                int marksChoice = sc.nextInt();

                switch (marksChoice) {

                case 1:

                    System.out.print("Student ID : ");
                    sid = sc.nextInt();

                    System.out.print("Course ID : ");
                    coid = sc.nextInt();

                    System.out.print("Marks : ");
                    int marks = sc.nextInt();

                    marksdata.addMarks(sid, coid, marks);

                    break;

                case 2:

                    marksdata.viewMarks();

                    break;

                case 3:

                    System.out.print("Mark ID : ");
                    int markId = sc.nextInt();

                    System.out.print("New Marks : ");
                    marks = sc.nextInt();

                    marksdata.updateMarks(markId, marks);

                    break;

                case 4:

                    System.out.print("Mark ID : ");
                    markId = sc.nextInt();

                    marksdata.deleteMarks(markId);

                    break;

                default:
                    System.out.println("Invalid Choice");
                }

                break;
            case 6:

                marksdata.generateResult();

                break;
                case 7:

                System.out.println("Thank You...");
                sc.close();
                System.exit(0);
                break;

            default:

                System.out.println("Invalid Choice.");
            }
        }
    }
}