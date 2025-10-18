package rs.ac.imievent.enrollment.dto;

public record StudentSummary(
        Long id, String firstName, String lastName, String email, String indexNumber, int yearOfStudy
) {}