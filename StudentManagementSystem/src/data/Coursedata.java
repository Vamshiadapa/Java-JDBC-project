package data;

import Databaseconnection.DBConnection;
import entity.Course;
import java.sql.*;
public class Coursedata {
    Connection con = DBConnection.getConnection();
    public void addCourse(Course c) {
        String sql = "insert into courses(course_name,duration,trainer_name,fees) values(?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getCourseName());
            ps.setString(2, c.getDuration());
            ps.setString(3, c.getTrainerName());
            ps.setDouble(4, c.getFees());
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Course Added Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void viewCourses() {
        String sql = "select * fron courses";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Course ID : " + rs.getInt("course_id"));
                System.out.println("Course Name : " + rs.getString("course_name"));
                System.out.println("Duration : " + rs.getString("duration"));
                System.out.println("Trainer : " + rs.getString("trainer_name"));
                System.out.println("Fees : " + rs.getDouble("fees"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateCourse(Course c) {
        String sql = "update courses set course_name=?, duration=?, trainer_name=?, fees=? where course_id=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getCourseName());
            ps.setString(2, c.getDuration());
            ps.setString(3, c.getTrainerName());
            ps.setDouble(4, c.getFees());
            ps.setInt(5, c.getCourseId());
            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Course Updated Successfully");
            else
                System.out.println("Course Not Found");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteCourse(int id) {

        String sql = "delete from courses where course_id=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Course Deleted Successfully");
            else
                System.out.println("Course Not Found");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}