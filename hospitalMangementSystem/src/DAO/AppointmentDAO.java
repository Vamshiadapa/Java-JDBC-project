package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import entity.Appointment;
import util.DBConnection;

public class AppointmentDAO {
    Connection con = DBConnection.getConnection();
    public void addAppointment(Appointment a) {
        String sql = "insert into appointments " +
                     "(patient_id, doctor_id, appointment_date, appointment_time, reason) " +
                     "values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, a.getPatientId());
            ps.setInt(2, a.getDoctorId());
            ps.setString(3, a.getAppointmentDate());
            ps.setString(4, a.getAppointmentTime());
            ps.setString(5, a.getReason());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Appointment Booked Successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void viewAppointments() {
        String sql = "select a.appointment_id, " +
                     "p.patient_name, " +
                     "d.doctor_name, " +
                     "d.specialization, " +
                     "a.appointment_date, " +
                     "a.appointment_time, " +
                     "a.reason " +
                     "from appointments a " +
                     "join patients p on a.patient_id = p.patient_id " +
                     "join doctors d on a.doctor_id = d.doctor_id";
        
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println( "Appointment ID : " +rs.getInt("appointment_id"));
                System.out.println( "Patient Name : " +rs.getString("patient_name") );
                System.out.println("Doctor Name : " + rs.getString("doctor_name"));              
                System.out.println("Specialization : " +rs.getString("specialization") );
                System.out.println("Appointment Date : " +rs.getString("appointment_date"));
                System.out.println( "Appointment Time : " + rs.getString("appointment_time"));
                System.out.println(  "Reason : " +rs.getString("reason") );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateAppointment(Appointment a) {
        String sql = "update appointments set " +
                     "patient_id=?, doctor_id=?, " +
                     "appointment_date=?, appointment_time=?, " +
                     "reason=? " +
                     "where appointment_id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, a.getPatientId());
            ps.setInt(2, a.getDoctorId());
            ps.setString(3, a.getAppointmentDate());
            ps.setString(4, a.getAppointmentTime());
            ps.setString(5, a.getReason());
            ps.setInt(6, a.getAppointmentId());

            int rows = ps.executeUpdate();
            
            if (rows > 0) {
                System.out.println("Appointment Updated Successfully");
            } else {
                System.out.println( "Appointment Not Found" );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAppointment(int appointmentId) {
        String sql ="delete from appointments where appointment_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, appointmentId);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Appointment Deleted Successfully");
            } else {
                System.out.println( "Appointment Not Found" );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}