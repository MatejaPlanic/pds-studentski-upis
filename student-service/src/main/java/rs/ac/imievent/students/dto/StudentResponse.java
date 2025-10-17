package rs.ac.imievent.students.dto;

public record StudentResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String indexNumber,
        int yearOfStudy
) {}
