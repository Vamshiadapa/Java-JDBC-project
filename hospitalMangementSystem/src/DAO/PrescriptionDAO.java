package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import entity.Prescription;
import util.DBConnection;

public class PrescriptionDAO {
    Connection con = DBConnection.getConnection();
    public void addPrescription(Prescription p) {
        String sql = "insert into prescriptions " +
                     "(appointment_id, medicine_name, dosage, duration, instructions) " +
                     "values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, p.getAppointmentId());
            ps.setString(2, p.getMedicineName());
            ps.setString(3, p.getDosage());
            ps.setString(4, p.getDuration());
            ps.setString(5, p.getInstructions());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println( "Prescription Added Successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void viewPrescriptions() {
        String sql = "select pr.prescription_id, " +
                     "p.patient_name, " +
                     "d.doctor_name, " +
                     "pr.medicine_name, " +
                     "pr.dosage, " +
                     "pr.duration, " +
                     "pr.instructions " +
                     "from prescriptions pr " +
                     "join appointments a " +
                     "on pr.appointment_id = a.appointment_id " +
                     "join patients p " +
                     "on a.patient_id = p.patient_id " +
                     "join doctors d " +
                     "on a.doctor_id = d.doctor_id";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Prescription ID : " + rs.getInt("prescription_id"));
                System.out.println( "Patient Name : " +rs.getString("patient_name") );
                System.out.println("Doctor Name : " + rs.getString("doctor_name"));
                System.out.println( "Medicine : " +rs.getString("medicine_name") );
                System.out.println( "Dosage : " +rs.getString("dosage"));
                System.out.println("Duration : " + rs.getString("duration") );
                System.out.println("Instructions : " + rs.getString("instructions"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updatePrescription(Prescription p) {

        String sql = "update prescriptions set " +
                     "appointment_id=?, medicine_name=?, " +
                     "dosage=?, duration=?, instructions=? " +
                     "where prescription_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, p.getAppointmentId());
            ps.setString(2, p.getMedicineName());
            ps.setString(3, p.getDosage());
            ps.setString(4, p.getDuration());
            ps.setString(5, p.getInstructions());
            ps.setInt(6, p.getPrescriptionId());
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Prescription Updated Successfully");
            } else {System.out.println("Prescription Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deletePrescription(int prescriptionId) {
        String sql ="delete from prescriptions where prescription_id=?";
        try {

            PreparedStatement ps =con.prepareStatement(sql);
            ps.setInt(1, prescriptionId);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println( "Prescription Deleted Successfully");
            } else {
                System.out.println( "Prescription Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}