package rs.ac.imievent.enrollment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Enrollment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long studentId;

    @NotBlank
    private String courseCode;

    @NotBlank
    private String courseName;

    @Builder.Default
    private LocalDate enrolledAt = LocalDate.now();

    @Builder.Default
    private String status = "CREATED"; // CREATED/CONFIRMED/CANCELLED
}
