package rs.ac.imievent.enrollment.dto;

import jakarta.validation.constraints.*;

public record EnrollmentCreateRequest(
        @NotNull Long studentId,
        @NotBlank String courseCode,
        @NotBlank String courseName
) {}