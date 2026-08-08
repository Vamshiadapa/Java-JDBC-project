package entity;
public class Student {

    private int studentId;
    private String studentName;
    private String gender;
    private int age;
    private String phone;
    private String email;
    private String department;

    public Student() {

    }

    public Student(int studentId, String studentName, String gender,
                   int age, String phone,
                   String email, String department) {

        this.studentId = studentId;
        this.studentName = studentName;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.department = department;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}