package rs.ac.imievent.enrollment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.imievent.enrollment.client.StudentClient;
import rs.ac.imievent.enrollment.dto.*;
import rs.ac.imievent.enrollment.model.Enrollment;
import rs.ac.imievent.enrollment.repo.EnrollmentRepository;

import java.util.List;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
@RequiredArgsConstructor
@Transactional
public class EnrollmentService {

    private final EnrollmentRepository repo;
    private final StudentClient studentClient;

    public EnrollmentResponse create(EnrollmentCreateRequest r) {
        var e = repo.save(Enrollment.builder()
                .studentId(r.studentId())
                .courseCode(r.courseCode())
                .courseName(r.courseName())
                .build());
        return map(e);
    }

    public EnrollmentResponse get(Long id) {
        return map(repo.findById(id).orElseThrow(() -> new RuntimeException("Enrollment not found")));
    }

    public List<EnrollmentResponse> all() {
        return repo.findAll().stream().map(this::map).toList();
    }

    @CircuitBreaker(name = "students", fallbackMethod = "detailsFallback")
    @Retry(name = "students")
    public EnrollmentDetails details(Long id) {
        var enr = repo.findById(id).orElseThrow(() -> new RuntimeException("Enrollment not found"));
        var student = studentClient.getStudent(enr.getStudentId());
        return new EnrollmentDetails(map(enr), student, "OK");
    }

    public EnrollmentDetails detailsFallback(Long id, Throwable t) {
        var enr = repo.findById(id).orElseThrow(() -> new RuntimeException("Enrollment not found"));
        var unknown = new StudentSummary(null, "N/A", "N/A", "N/A", "N/A", 0);
        return new EnrollmentDetails(map(enr), unknown,
                "Student servis nije dostupan (" + t.getClass().getSimpleName() + ")");
    }

    private EnrollmentResponse map(Enrollment e) {
        return new EnrollmentResponse(e.getId(), e.getStudentId(),
                e.getCourseCode(), e.getCourseName(), e.getStatus(), e.getEnrolledAt());
    }

    public EnrollmentResponse update(Long id, EnrollmentUpdateRequest r) {
        var e = repo.findById(id).orElseThrow(() -> new RuntimeException("Enrollment not found"));
        e.setStudentId(r.studentId());
        e.setCourseCode(r.courseCode());
        e.setCourseName(r.courseName());
        return map(repo.save(e));
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new RuntimeException("Enrollment not found");
        repo.deleteById(id);
    }
}
