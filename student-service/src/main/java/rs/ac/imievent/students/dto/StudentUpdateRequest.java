package rs.ac.imievent.students.dto;

import jakarta.validation.constraints.*;

public record StudentUpdateRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @Email @NotBlank String email,
        @NotBlank String indexNumber,
        @Min(1) @Max(8) int yearOfStudy
) {}
