package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import entity.Patient;
import util.DBConnection;

public class PatientDAO {
    Connection con = DBConnection.getConnection();
    public void addPatient(Patient p) {
        String sql = "insert into patients " +"(patient_name, age, gender, phone, address) " +"values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getPatientName());
            ps.setInt(2, p.getAge());
            ps.setString(3, p.getGender());
            ps.setString(4, p.getPhone());
            ps.setString(5, p.getAddress());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Patient Added Successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void viewPatients() {
        String sql = "select * from patients";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {            
                System.out.println("Patient ID : " + rs.getInt("patient_id"));
                System.out.println( "Name : " + rs.getString("patient_name"));
                System.out.println("Age : "+ rs.getInt("age"));
                System.out.println("Gender : "+ rs.getString("gender"));
                System.out.println("Phone :"  + rs.getString("phone"));
                System.out.println("Address : "+ rs.getString("address")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updatePatient(Patient p) {
        String sql = "update patients set " +
                     "patient_name=?, age=?, gender=?, " +
                     "phone=?, address=? " +
                     "where patient_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getPatientName());
            ps.setInt(2, p.getAge());
            ps.setString(3, p.getGender());
            ps.setString(4, p.getPhone());
            ps.setString(5, p.getAddress());
            ps.setInt(6, p.getPatientId());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println( "Patient Updated Successfully");
            } else {
                System.out.println( "Patient Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deletePatient(int patientId) {
        String sql =
                "Delete FROM patients WHERE patient_id=?";
        try {
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ps.setInt(1, patientId);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Patient Deleted Successfully" );
            } else {
                System.out.println("Patient Not Found" );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}