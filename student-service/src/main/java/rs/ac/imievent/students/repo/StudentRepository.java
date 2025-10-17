package rs.ac.imievent.students.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.imievent.students.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> { }