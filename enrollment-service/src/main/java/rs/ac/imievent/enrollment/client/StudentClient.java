package rs.ac.imievent.enrollment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import rs.ac.imievent.enrollment.dto.StudentSummary;

@FeignClient(name = "student-service", path = "/api/students")
public interface StudentClient {
    @GetMapping("/{id}")
    StudentSummary getStudent(@PathVariable("id") Long id);
}
