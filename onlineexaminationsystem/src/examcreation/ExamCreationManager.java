package examcreation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamCreationManager {
    public static class Exam {
        private int examId;
        private String title;
        private String subject;
        private int totalMarks;
        private int durationMinutes;
        public Exam(int examId, String title, String subject, int totalMarks, int durationMinutes) {
            this.examId = examId;
            this.title = title;
            this.subject = subject;
            this.totalMarks = totalMarks;
            this.durationMinutes = durationMinutes;
        }
        public int getExamId() { return examId; }
        public void setExamId(int examId) { this.examId = examId; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getSubject() { return subject; }
        public void setSubject(String subject) { this.subject = subject; }

        public int getTotalMarks() { return totalMarks; }
        public void setTotalMarks(int totalMarks) { this.totalMarks = totalMarks; }

        public int getDurationMinutes() { return durationMinutes; }
        public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }

        public String toString() {
            return String.format("Exam[id=%d, title=%s, subject=%s, totalMarks=%d, durationMinutes=%d]",
                    examId, title, subject, totalMarks, durationMinutes);
        }
    }
    public int create(Connection conn, String title, String subject, int totalMarks, int durationMinutes) throws SQLException {
        String sql = "insert into exams (title, subject, total_marks, duration_minutes) values (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, title);
            stmt.setString(2, subject);
            stmt.setInt(3, totalMarks);
            stmt.setInt(4, durationMinutes);
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
            }
        }
        return -1;
    }
    public List<Exam> findAll(Connection conn) throws SQLException {
        List<Exam> results = new ArrayList<>();
        String sql = "select * from exams order by exam_id";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) results.add(mapRow(rs));
        }
        return results;
    }
    public Exam findById(Connection conn, int examId) throws SQLException {
        String sql = "select * from exams where exam_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, examId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }
    public boolean update(Connection conn, Exam exam) throws SQLException {
        String sql = "update exams set title = ?, subject = ?, total_marks = ?, duration_minutes = ? where exam_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, exam.getTitle());
            stmt.setString(2, exam.getSubject());
            stmt.setInt(3, exam.getTotalMarks());
            stmt.setInt(4, exam.getDurationMinutes());
            stmt.setInt(5, exam.getExamId());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean delete(Connection conn, int examId) throws SQLException {
        String sql = "delete from exams where exam_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, examId);
            return stmt.executeUpdate() > 0;
        }
    }

    private Exam mapRow(ResultSet rs) throws SQLException {
        return new Exam(
                rs.getInt("exam_id"),
                rs.getString("title"),
                rs.getString("subject"),
                rs.getInt("total_marks"),
                rs.getInt("duration_minutes")
        );
    }
}