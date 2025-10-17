package rs.ac.imievent.students.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.imievent.students.dto.StudentCreateRequest;
import rs.ac.imievent.students.dto.StudentResponse;
import rs.ac.imievent.students.model.Student;
import rs.ac.imievent.students.repo.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {
    private final StudentRepository repo;

    public StudentResponse create(StudentCreateRequest r) {
        Student s = repo.save(Student.builder()
                .firstName(r.firstName())
                .lastName(r.lastName())
                .email(r.email())
                .indexNumber(r.indexNumber())
                .yearOfStudy(r.yearOfStudy())
                .build());
        return map(s);
    }

    public StudentResponse get(Long id) {
        return map(repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found")));
    }

    public List<StudentResponse> all() {
        return repo.findAll().stream().map(this::map).toList();
    }

    public StudentResponse update(Long id, StudentCreateRequest r) {
        Student s = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        s.setFirstName(r.firstName());
        s.setLastName(r.lastName());
        s.setEmail(r.email());
        s.setIndexNumber(r.indexNumber());
        s.setYearOfStudy(r.yearOfStudy());
        return map(s);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    private StudentResponse map(Student s) {
        return new StudentResponse(s.getId(), s.getFirstName(), s.getLastName(),
                s.getEmail(), s.getIndexNumber(), s.getYearOfStudy());
    }
}
