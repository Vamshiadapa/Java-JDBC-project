package entity;
public class Prescription {
    private int prescriptionId;
    private int appointmentId;
    private String medicineName;
    private String dosage;
    private String duration;
    private String instructions;
    public Prescription() {
    }
    public Prescription(int prescriptionId, int appointmentId,
                        String medicineName, String dosage,
                        String duration, String instructions) {
        this.prescriptionId = prescriptionId;
        this.appointmentId = appointmentId;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.duration = duration;
        this.instructions = instructions;
    }
    public int getPrescriptionId() {
        return prescriptionId;
    }
    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }
    public int getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
    public String getMedicineName() {
        return medicineName;
    }
    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }
    public String getDosage() {
        return dosage;
    }
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getInstructions() {
        return instructions;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}