package rs.ac.imievent.enrollment.dto;

public record EnrollmentResponse(
        Long id, Long studentId, String courseCode, String courseName, String status, java.time.LocalDate enrolledAt
) {}
