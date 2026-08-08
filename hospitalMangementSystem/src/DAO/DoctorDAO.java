package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import entity.Doctor;
import util.DBConnection;

public class DoctorDAO {
    Connection con = DBConnection.getConnection();
    public void addDoctor(Doctor d) {
        String sql = "insert into doctors " +
                     "(doctor_name, specialization, phone, experience) " +
                     "values (?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, d.getDoctorName());
            ps.setString(2, d.getSpecialization());
            ps.setString(3, d.getPhone());
            ps.setInt(4, d.getExperience());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Doctor Added Successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void viewDoctors() {
        String sql = "select * from doctors";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Doctor ID : " + rs.getInt("doctor_id"));

                System.out.println( "Name : " +rs.getString("doctor_name"));

                System.out.println("Specialization : " +rs.getString("specialization") );

                System.out.println("Phone : " +rs.getString("phone") );

                System.out.println("Experience : " +rs.getInt("experience"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateDoctor(Doctor d) {
        String sql = "update doctors set " +
                     "doctor_name=?, specialization=?, " +
                     "phone=?, experience=? " +
                     "where doctor_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, d.getDoctorName());
            ps.setString(2, d.getSpecialization());
            ps.setString(3, d.getPhone());
            ps.setInt(4, d.getExperience());
            ps.setInt(5, d.getDoctorId());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Doctor Updated Successfully");
            } else {
                System.out.println( "Doctor Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDoctor(int doctorId) {

        String sql = "delete from doctors where doctor_id=?";
        try {
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setInt(1, doctorId);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println( "Doctor Deleted Successfully");
            } else {
                System.out.println("Doctor Not Found" );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}