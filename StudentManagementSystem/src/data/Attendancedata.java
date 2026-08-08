package data;

import Databaseconnection.DBConnection;
import java.sql.*;
public class Attendancedata {
    Connection con = DBConnection.getConnection();
    public void markAttendance(int studentId, int courseId,
                               String attendanceDate, String status) {
        String sql = "insert into attendance(student_id, course_id, attendance_date, status) values(?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.setString(3, attendanceDate);
            ps.setString(4, status);
            int rows = ps.executeUpdate();
            
            if(rows > 0)
                System.out.println("Attendance Marked Successfully.");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void viewAttendance() {
        String sql = "select a.attendance_id, s.student_name, c.course_name, " +
                     "a.attendance_date, a.status " +
                     "from attendance a " +
                     "join students s on a.student_id = s.student_id " +
                     "join courses c on a.course_id = c.course_id";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                System.out.println("Attendance ID : " + rs.getInt("attendance_id"));
                System.out.println("Student Name  : " + rs.getString("student_name"));
                System.out.println("Course Name   : " + rs.getString("course_name"));
                System.out.println("Date          : " + rs.getString("attendance_date"));
                System.out.println("Status        : " + rs.getString("status"));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void updateAttendance(int attendanceId, String status) {

        String sql = "update attendance SET status=? where attendance_id=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, attendanceId);
            int rows = ps.executeUpdate();
            if(rows > 0)
                System.out.println("Attendance Updated Successfully.");
            else
                System.out.println("Attendance Record Not Found.");

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
    public void deleteAttendance(int attendanceId) {

        String sql = "delete from attendance where attendance_id=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, attendanceId);

            int rows = ps.executeUpdate();

            if(rows > 0)
                System.out.println("Attendance Deleted Successfully.");
            else
                System.out.println("Attendance Record Not Found.");

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}