package entity;
public class Course {

    private int courseId;
    private String courseName;
    private String duration;
    private String trainerName;
    private double fees;

    public Course() {

    }

    public Course(int courseId, String courseName,String duration, String trainerName,double fees) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.duration = duration;
        this.trainerName = trainerName;
        this.fees = fees;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }
}