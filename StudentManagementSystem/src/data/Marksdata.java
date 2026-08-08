package data;

import Databaseconnection.DBConnection;
import java.sql.*;
public class Marksdata {
    Connection con = DBConnection.getConnection();
    public void addMarks(int studentId, int courseId, int marks) {
        String sql = "insert into marks(student_id,course_id,marks) values(?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.setInt(3, marks);
            int rows = ps.executeUpdate();
            if(rows > 0)
                System.out.println("Marks Added Successfully.");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void viewMarks() {
        String sql = "select m.mark_id, s.student_name, c.course_name, m.marks " +
                     "from marks m " +
                     "join students s Oon m.student_id=s.student_id " +
                     "join courses c on m.course_id=c.course_id";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                System.out.println("Mark ID      : " + rs.getInt("mark_id"));
                System.out.println("Student Name : " + rs.getString("student_name"));
                System.out.println("Course Name  : " + rs.getString("course_name"));
                System.out.println("Marks        : " + rs.getInt("marks"));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void updateMarks(int markId, int marks) {
        String sql = "update marks SET marks=? where mark_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, marks);
            ps.setInt(2, markId);
            int rows = ps.executeUpdate();
            if(rows > 0)
                System.out.println("Marks Updated Successfully.");
            else
                System.out.println("Record Not Found.");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteMarks(int markId) {
        String sql = "delete FROM marks where mark_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, markId);
            int rows = ps.executeUpdate();
            if(rows > 0)
                System.out.println("Marks Deleted Successfully.");
            else
                System.out.println("Record Not Found.");

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
    public void generateResult() {

        String sql =
            "select s.student_name, c.course_name, m.marks, " +
            "case " +
            "when m.marks >= 90 then 'A+' " +
            "when m.marks >= 80 then 'A' " +
            "when m.marks >= 70 thne 'B' " +
            "when m.marks >= 60 then 'C' " +
            "else 'Fail' end as Grade " +
            "from marks m " +
            "join students s on m.student_id=s.student_id " +
            "join courses c on m.course_id=c.course_id";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                System.out.println("--------------------------------");
                System.out.println("Student : " + rs.getString("student_name"));
                System.out.println("Course  : " + rs.getString("course_name"));
                System.out.println("Marks   : " + rs.getInt("marks"));
                System.out.println("Grade   : " + rs.getString("Grade"));
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}