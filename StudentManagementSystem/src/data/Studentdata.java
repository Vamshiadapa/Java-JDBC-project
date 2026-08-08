package data;
import Databaseconnection.DBConnection;
import entity.Student;
import java.sql.*;

public class Studentdata {
    Connection con = DBConnection.getConnection();
    public void addStudent(Student s) {
        String sql = "insert into students(student_name,gender,age,phone,email,department) values(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, s.getStudentName());
            ps.setString(2, s.getGender());
            ps.setInt(3, s.getAge());
            ps.setString(4, s.getPhone());
            ps.setString(5, s.getEmail());
            ps.setString(6, s.getDepartment());
            int rows = ps.executeUpdate();
            if(rows > 0)
                System.out.println("Student Added Successfully");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void viewStudents() {
        String sql = "select * from students";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                System.out.println("ID : " + rs.getInt("student_id"));
                System.out.println("Name : " + rs.getString("student_name"));
                System.out.println("Gender : " + rs.getString("gender"));
                System.out.println("Age : " + rs.getInt("age"));
                System.out.println("Phone : " + rs.getString("phone"));
                System.out.println("Email : " + rs.getString("email"));
                System.out.println("Department : " + rs.getString("department"));
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
    public void updateStudent(Student s) {
    	String sql = "updata students set student_name=?, gender=?, age=?, phone=?, email=?, department=? where student_id=?";
    	
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, s.getStudentName());
            ps.setString(2, s.getGender());
            ps.setInt(3, s.getAge());
            ps.setString(4, s.getPhone());
            ps.setString(5, s.getEmail());
            ps.setString(6, s.getDepartment());
            ps.setInt(7, s.getStudentId());
            int rows = ps.executeUpdate();
            if(rows > 0)
                System.out.println("Student Updated Successfully");
            else
                System.out.println("Student Not Found");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteStudent(int id) {
        String sql = "delete from students where student_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if(rows > 0)
                System.out.println("Student Deleted Successfully");
            else
                System.out.println("Student Not Found");
        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}