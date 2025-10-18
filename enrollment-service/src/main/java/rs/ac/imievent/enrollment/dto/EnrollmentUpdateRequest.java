package rs.ac.imievent.enrollment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnrollmentUpdateRequest(
        @NotNull Long studentId,
        @NotBlank String courseCode,
        @NotBlank String courseName
) {}
