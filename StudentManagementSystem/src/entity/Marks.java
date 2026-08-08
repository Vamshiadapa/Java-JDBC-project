package entity;

public class Marks {

    private int markId;
    private int studentId;
    private int courseId;
    private int marks;

    public Marks() {

    }

    public Marks(int markId, int studentId, int courseId, int marks) {

        this.markId = markId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.marks = marks;
    }

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}