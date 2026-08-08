package main;
import common.ConnectionManager;
import examcreation.ExamCreationManager;
import login.StudentLoginManager;
import questionmanagement.QuestionManager;
import resultgeneration.ResultManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OnlineExamination{

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentLoginManager loginManager = new StudentLoginManager();
    private static final ExamCreationManager examManager = new ExamCreationManager();
    private static final QuestionManager questionManager = new QuestionManager();
    private static final ResultManager resultManager = new ResultManager();

    private static StudentLoginManager.Student currentStudent;

    public static void main(String[] args) {
        try {
            Connection conn = ConnectionManager.get();
            boolean running = true;
            while (running) {
                printMainMenu();
                int choice = readInt();
                switch (choice) {
                    case 1: studentLoginMenu(conn); break;
                    case 2: examCreationMenu(conn); break;
                    case 3: questionManagementMenu(conn); break;
                    case 4: resultGenerationMenu(conn); break;
                    case 0: running = false; break;
                    default: System.out.println("invalid choice");
                }
            }
            ConnectionManager.close();
            System.out.println("goodbye!");
        } catch (SQLException e) {
            System.err.println("database error: " + e.getMessage());
        }
    }

    private static void printMainMenu() {
        System.out.println("\n===== online examination system =====");
        String who = currentStudent != null ? " (logged in as " + currentStudent.getName() + ")" : "";
        System.out.println("1. Student Login" + who);
        System.out.println("2. Exam Creation");
        System.out.println("3. Question Management");
        System.out.println("4. Result Generation");
        System.out.println("0. Exit");
        System.out.print("choose an option: ");
    }

    private static void studentLoginMenu(Connection conn) throws SQLException {
        System.out.println("\n-- student login --");
        System.out.println("1. Register  2. Login  3. View All Students  0. Back");
        int choice = readInt();
        switch (choice) {
            case 1:
                System.out.print("name: "); String name = scanner.nextLine();
                System.out.print("email: "); String email = scanner.nextLine();
                System.out.print("password: "); String password = scanner.nextLine();
                System.out.print("roll number: "); String roll = scanner.nextLine();
                int id = loginManager.register(conn, name, email, password, roll);
                System.out.println("registered student with id: " + id);
                break;
            case 2:
                System.out.print("email: "); String loginEmail = scanner.nextLine();
                System.out.print("password: "); String loginPassword = scanner.nextLine();
                StudentLoginManager.Student student = loginManager.authenticate(conn, loginEmail, loginPassword);
                if (student == null) {
                    System.out.println("invalid credentials.");
                } else {
                    currentStudent = student;
                    System.out.println("welcome, " + student.getName() + "!");
                }
                break;
            case 3:
                loginManager.findAll(conn).forEach(System.out::println);
                break;
            default: break;
        }
    }

    private static void examCreationMenu(Connection conn) throws SQLException {
        System.out.println("\n-- exam creation --");
        System.out.println("1. Create Exam  2. View All  3. Update  4. Delete  0. Back");
        int choice = readInt();
        switch (choice) {
            case 1:
                System.out.print("title: "); String title = scanner.nextLine();
                System.out.print("subject: "); String subject = scanner.nextLine();
                System.out.print("total marks: "); int totalMarks = readInt();
                System.out.print("duration (minutes): "); int duration = readInt();
                int id = examManager.create(conn, title, subject, totalMarks, duration);
                System.out.println("created exam with id: " + id);
                break;
            case 2:
                examManager.findAll(conn).forEach(System.out::println);
                break;
            case 3:
                System.out.print("exam id to update: "); int uid = readInt();
                ExamCreationManager.Exam exam = examManager.findById(conn, uid);
                if (exam == null) { System.out.println("not found."); break; }
                System.out.print("new title: "); exam.setTitle(scanner.nextLine());
                System.out.print("new duration (minutes): "); exam.setDurationMinutes(readInt());
                System.out.println(examManager.update(conn, exam) ? "updated." : "update failed.");
                break;
            case 4:
                System.out.print("exam id to delete: "); int did = readInt();
                System.out.println(examManager.delete(conn, did) ? "deleted." : "delete failed.");
                break;
            default: break;
        }
    }

    private static void questionManagementMenu(Connection conn) throws SQLException {
        System.out.println("\n-- question management --");
        System.out.println("1. Add Question  2. View by Exam  3. View All (joined)  4. Delete  0. Back");
        int choice = readInt();
        switch (choice) {
            case 1:
                System.out.print("exam id: "); int examId = readInt();
                System.out.print("question text: "); String text = scanner.nextLine();
                System.out.print("option a: "); String a = scanner.nextLine();
                System.out.print("option b: "); String b = scanner.nextLine();
                System.out.print("option c: "); String c = scanner.nextLine();
                System.out.print("option d: "); String d = scanner.nextLine();
                System.out.print("correct option (a/b/c/d): "); String correct = scanner.nextLine();
                System.out.print("marks: "); int marks = readInt();
                int qid = questionManager.add(conn, examId, text, a, b, c, d, correct, marks);
                System.out.println("added question with id: " + qid);
                break;
            case 2:
                System.out.print("exam id: "); int eid = readInt();
                questionManager.findByExamId(conn, eid).forEach(System.out::println);
                break;
            case 3:
                questionManager.findAllWithExamTitle(conn).forEach(System.out::println);
                break;
            case 4:
                System.out.print("question id to delete: "); int delId = readInt();
                System.out.println(questionManager.delete(conn, delId) ? "deleted." : "delete failed.");
                break;
            default: break;
        }
    }

    private static void resultGenerationMenu(Connection conn) throws SQLException {
        System.out.println("\n-- result generation --");
        System.out.println("1. Take Exam (auto-scored)  2. View All Results (joined)  3. My History  4. Exam Stats (aggregate)  5. Leaderboard (aggregate)  0. Back");
        int choice = readInt();
        switch (choice) {
            case 1:
                takeExam(conn);
                break;
            case 2:
                resultManager.findAllWithDetails(conn).forEach(System.out::println);
                break;
            case 3:
                if (currentStudent == null) { System.out.println("log in first."); break; }
                resultManager.findByStudentId(conn, currentStudent.getStudentId()).forEach(System.out::println);
                break;
            case 4:
                System.out.print("exam id: "); int examId = readInt();
                System.out.println(resultManager.getExamStats(conn, examId));
                break;
            case 5:
                List<ResultManager.LeaderboardEntry> leaderboard = resultManager.getLeaderboard(conn);
                leaderboard.forEach(System.out::println);
                break;
            default: break;
        }
    }

    private static void takeExam(Connection conn) throws SQLException {
        if (currentStudent == null) { System.out.println("log in first."); return; }

        System.out.print("exam id to take: ");
        int examId = readInt();
        List<QuestionManager.Question> questions = questionManager.findByExamId(conn, examId);
        if (questions.isEmpty()) { System.out.println("no questions found for that exam."); return; }

        Map<Integer, String> answers = new HashMap<>();
        for (QuestionManager.Question q : questions) {
            System.out.println("\n" + q.getQuestionText());
            System.out.println("a) " + q.getOptionA());
            System.out.println("b) " + q.getOptionB());
            System.out.println("c) " + q.getOptionC());
            System.out.println("d) " + q.getOptionD());
            System.out.print("your answer: ");
            answers.put(q.getQuestionId(), scanner.nextLine());
        }

        int resultId = resultManager.submitAndScore(conn, currentStudent.getStudentId(), examId, answers);
        System.out.println("exam submitted, result id: " + resultId);
    }

    private static int readInt() {
        while (!scanner.hasNextInt()) {
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}