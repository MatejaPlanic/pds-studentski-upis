package rs.ac.imievent.enrollment.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.imievent.enrollment.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> { }
