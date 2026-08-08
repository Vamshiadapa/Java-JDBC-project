import java.util.Scanner;
import DAO.PatientDAO;
import DAO.DoctorDAO;
import DAO.AppointmentDAO;
import DAO.PrescriptionDAO;
import entity.Patient;
import entity.Doctor;
import entity.Appointment;
import entity.Prescription;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PatientDAO patientDAO = new PatientDAO();
        DoctorDAO doctorDAO = new DoctorDAO();
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        PrescriptionDAO prescriptionDAO = new PrescriptionDAO();

        while (true) {
            System.out.println(" *** hospital management system ***");
            System.out.println("1. patient management");
            System.out.println("2. doctor management");
            System.out.println("3. appointment management");
            System.out.println("4. prescription management");
            System.out.println("5. exit");
            System.out.print("enter your choice : ");
            int choice = sc.nextInt();

            switch (choice) {
            case 1:
                System.out.println("\n------ patient management ------");
                System.out.println("1. add patient");
                System.out.println("2. view patients");
                System.out.println("3. update patient");
                System.out.println("4. delete patient");
                System.out.print("enter your choice : ");
                int patientChoice = sc.nextInt();

                switch (patientChoice) {
                case 1:
                    Patient p = new Patient();
                    sc.nextLine();
                    System.out.print("enter patient name : ");
                    p.setPatientName(sc.nextLine());
                    System.out.print("enter age : ");
                    p.setAge(sc.nextInt());
                    sc.nextLine();
                    System.out.print("enter gender : ");
                    p.setGender(sc.nextLine());
                    System.out.print("enter phone : ");
                    p.setPhone(sc.nextLine());
                    System.out.print("enter address : ");
                    p.setAddress(sc.nextLine());
                    patientDAO.addPatient(p);
                    break;

                case 2:
                    patientDAO.viewPatients();
                    break;

                case 3:
                    Patient updatePatient = new Patient();
                    System.out.print("enter patient id : ");
                    updatePatient.setPatientId(sc.nextInt());
                    sc.nextLine();
                    System.out.print("enter patient name : ");
                    updatePatient.setPatientName(sc.nextLine());
                    System.out.print("enter age : ");
                    updatePatient.setAge(sc.nextInt());
                    sc.nextLine();
                    System.out.print("enter gender : ");
                    updatePatient.setGender(sc.nextLine());
                    System.out.print("enter phone : ");
                    updatePatient.setPhone(sc.nextLine());
                    System.out.print("enter address : ");
                    updatePatient.setAddress(sc.nextLine());
                    patientDAO.updatePatient(updatePatient);
                    break;

                case 4:
                    System.out.print("enter patient id : ");
                    int patientId = sc.nextInt();
                    patientDAO.deletePatient(patientId);
                    break;

                default:
                    System.out.println("invalid choice");
                }
                break;
                
            case 2:
                System.out.println("\n------ doctor management ------");
                System.out.println("1. add doctor");
                System.out.println("2. view doctors");
                System.out.println("3. update doctor");
                System.out.println("4. delete doctor");
                System.out.print("enter your choice : ");
                int doctorChoice = sc.nextInt();

                switch (doctorChoice) {
                case 1:
                    Doctor d = new Doctor();
                    sc.nextLine();
                    System.out.print("enter doctor name : ");
                    d.setDoctorName(sc.nextLine());
                    System.out.print("enter specialization : ");
                    d.setSpecialization(sc.nextLine());
                    System.out.print("enter phone : ");
                    d.setPhone(sc.nextLine());
                    System.out.print("enter experience : ");
                    d.setExperience(sc.nextInt());
                    doctorDAO.addDoctor(d);
                    break;

                case 2:
                    doctorDAO.viewDoctors();
                    break;
                    
                case 3:
                    Doctor updateDoctor = new Doctor();
                    System.out.print("enter doctor id : ");
                    updateDoctor.setDoctorId(sc.nextInt());
                    sc.nextLine();
                    System.out.print("enter doctor name : ");
                    updateDoctor.setDoctorName(sc.nextLine());
                    System.out.print("enter specialization : ");
                    updateDoctor.setSpecialization(sc.nextLine());
                    System.out.print("enter phone : ");
                    updateDoctor.setPhone(sc.nextLine());
                    System.out.print("enter experience : ");
                    updateDoctor.setExperience(sc.nextInt());
                    doctorDAO.updateDoctor(updateDoctor);
                    break;

                case 4:
                    System.out.print("enter doctor id : ");
                    int doctorId = sc.nextInt();
                    doctorDAO.deleteDoctor(doctorId);
                    break;

                default:
                    System.out.println("invalid choice");
                }
                break;
                
            case 3:
                System.out.println("\n------ appointment management ------");
                System.out.println("1. book appointment");
                System.out.println("2. view appointments");
                System.out.println("3. update appointment");
                System.out.println("4. delete appointment");
                System.out.print("enter your choice : ");
                int appointmentChoice = sc.nextInt();

                switch (appointmentChoice) {
                case 1:
                    Appointment a = new Appointment();
                    System.out.print("enter patient id : ");
                    a.setPatientId(sc.nextInt());
                    System.out.print("enter doctor id : ");
                    a.setDoctorId(sc.nextInt());
                    sc.nextLine();
                    System.out.print("enter appointment date (yyyy-mm-dd) : ");
                    a.setAppointmentDate(sc.nextLine());
                    System.out.print("enter appointment time (hh:mm:ss) : ");
                    a.setAppointmentTime(sc.nextLine());
                    System.out.print("enter reason : ");
                    a.setReason(sc.nextLine());
                    appointmentDAO.addAppointment(a);
                    break;

                case 2:
                    appointmentDAO.viewAppointments();
                    break;

                case 3:
                    Appointment updateAppointment = new Appointment();
                    System.out.print("enter appointment id : ");
                    updateAppointment.setAppointmentId(sc.nextInt());
                    System.out.print("enter patient id : ");
                    updateAppointment.setPatientId(sc.nextInt());
                    System.out.print("enter doctor id : ");
                    updateAppointment.setDoctorId(sc.nextInt());
                    sc.nextLine();
                    System.out.print("enter appointment date (yyyy-mm-dd) : ");
                    updateAppointment.setAppointmentDate(sc.nextLine());
                    System.out.print("enter appointment time (hh:mm:ss) : ");
                    updateAppointment.setAppointmentTime(sc.nextLine());
                    System.out.print("enter reason : ");
                    updateAppointment.setReason(sc.nextLine());
                    appointmentDAO.updateAppointment(updateAppointment);
                    break;

                case 4:
                    System.out.print("enter appointment id : ");
                    int appointmentId = sc.nextInt();
                    appointmentDAO.deleteAppointment(appointmentId);
                    break;

                default:
                    System.out.println("invalid choice");
                }

                break;
                
            case 4:
                System.out.println("\n------ prescription management ------");
                System.out.println("1. add prescription");
                System.out.println("2. view prescriptions");
                System.out.println("3. update prescription");
                System.out.println("4. delete prescription");
                System.out.print("enter your choice : ");
                int prescriptionChoice = sc.nextInt();
                switch (prescriptionChoice) {

                case 1:
                    Prescription pr = new Prescription();
                    System.out.print("enter appointment id : ");
                    pr.setAppointmentId(sc.nextInt());
                    sc.nextLine();
                    System.out.print("enter medicine name : ");
                    pr.setMedicineName(sc.nextLine());
                    System.out.print("enter dosage : ");
                    pr.setDosage(sc.nextLine());
                    System.out.print("enter duration : ");
                    pr.setDuration(sc.nextLine());
                    System.out.print("enter instructions : ");
                    pr.setInstructions(sc.nextLine());
                    prescriptionDAO.addPrescription(pr);
                    break;

                case 2:
                    prescriptionDAO.viewPrescriptions();
                    break;

                case 3:
                    Prescription updatePrescription = new Prescription();
                    System.out.print("enter prescription id : ");
                    updatePrescription.setPrescriptionId(sc.nextInt());
                    System.out.print("enter appointment id : ");
                    updatePrescription.setAppointmentId(sc.nextInt());
                    sc.nextLine();
                    System.out.print("enter medicine name : ");
                    updatePrescription.setMedicineName(sc.nextLine());
                    System.out.print("enter dosage : ");
                    updatePrescription.setDosage(sc.nextLine());
                    System.out.print("enter duration : ");
                    updatePrescription.setDuration(sc.nextLine());
                    System.out.print("enter instructions : ");
                    updatePrescription.setInstructions(sc.nextLine());
                    prescriptionDAO.updatePrescription(updatePrescription);
                    break;

                case 4:
                    System.out.print("enter prescription id : ");
                    int prescriptionId = sc.nextInt();
                    prescriptionDAO.deletePrescription(prescriptionId);
                    break;

                default:
                    System.out.println("invalid choice");
                }
                break;

            case 5:
                System.out.println("thank you");
                sc.close();
                System.exit(0);
                break;
            default:
                System.out.println("invalid choice");
            }
        }
    }
}