package rs.ac.imievent.enrollment.dto;

public record EnrollmentDetails(
        EnrollmentResponse enrollment, StudentSummary student, String note
) {}