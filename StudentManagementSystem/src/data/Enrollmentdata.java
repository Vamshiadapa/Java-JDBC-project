package data;

import Databaseconnection.DBConnection;
import java.sql.*;
public class Enrollmentdata {
    Connection con = DBConnection.getConnection();
    public void enrollStudent(int studentId, int courseId, String enrollmentDate) {
        String enrollSql = "insert into enrollments(student_id,course_id,enrollment_date) values(?,?,?)";
        String attendanceSql ="insert int0 attendance(student_id,course_id,attendance_date,status) values(?,?,?,?)";

        try {
            con.setAutoCommit(false);
            PreparedStatement ps1 = con.prepareStatement(enrollSql);
            ps1.setInt(1, studentId);
            ps1.setInt(2, courseId);
            ps1.setString(3, enrollmentDate);
            ps1.executeUpdate();
            PreparedStatement ps2 = con.prepareStatement(attendanceSql);
            ps2.setInt(1, studentId);
            ps2.setInt(2, courseId);
            ps2.setString(3, enrollmentDate);
            ps2.setString(4, "Present");
            ps2.executeUpdate();
            con.commit();
            System.out.println("Student Enrolled Successfully.");
        } catch (Exception e) {

            try {
                con.rollback();
                System.out.println("Transaction Rolled Back.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        }

        finally {

            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void viewEnrollments() {

        String sql =
                "select e.enrollment_id, s.student_name, c.course_name, e.enrollment_date " +
                "from enrollments e " +
                "join students s on e.student_id = s.student_id " +
                "join courses c om e.course_id = c.course_id";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Enrollment ID : " + rs.getInt("enrollment_id"));
                System.out.println("Student Name  : " + rs.getString("student_name"));
                System.out.println("Course Name   : " + rs.getString("course_name"));
                System.out.println("Date          : " + rs.getString("enrollment_date"));
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    public void deleteEnrollment(int enrollmentId) {
        String sql = "delete from enrollments where enrollment_id=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, enrollmentId);
            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Enrollment Deleted Successfully.");
            else
                System.out.println("Enrollment Not Found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}