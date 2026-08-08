package login;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentLoginManager {
    public static class Student {
        private int studentId;
        private String name;
        private String email;
        private String password;
        private String rollNumber;
        
        public Student() {}
        public Student(int studentId, String name, String email, String password, String rollNumber) {
            this.studentId = studentId;
            this.name = name;
            this.email = email;
            this.password = password;
            this.rollNumber = rollNumber;
        }
        public int getStudentId() { return studentId; }
        public void setStudentId(int studentId) { this.studentId = studentId; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

        public String getRollNumber() { return rollNumber; }
        public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }

        public String toString() {
            return String.format("Student[id=%d, name=%s, email=%s, roll=%s]",
                    studentId, name, email, rollNumber);
        }
    }

    public int register(Connection conn, String name, String email, String password, String rollNumber) throws SQLException {
        String sql = "insert into students (name, email, password, roll_number) values (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, rollNumber);
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        }
        return -1;
    }
    public List<Student> findAll(Connection conn) throws SQLException {
        List<Student> results = new ArrayList<>();
        String sql = "select * from students order by student_id";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) results.add(mapRow(rs));
        }
        return results;
    }
    public Student findById(Connection conn, int studentId) throws SQLException {
        String sql = "select * from students where student_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }

    public Student authenticate(Connection conn, String email, String password) throws SQLException {
        String sql = "select * from students where email = ? and password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }
    public boolean update(Connection conn, Student student) throws SQLException {
        String sql = "update students set name = ?, email = ?, password = ?, roll_number = ? where student_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getPassword());
            stmt.setString(4, student.getRollNumber());
            stmt.setInt(5, student.getStudentId());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean delete(Connection conn, int studentId) throws SQLException {
        String sql = "delete from students where student_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            return stmt.executeUpdate() > 0;
        }
    }

    private Student mapRow(ResultSet rs) throws SQLException {
        return new Student(
                rs.getInt("student_id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("roll_number")
        );
    }
}