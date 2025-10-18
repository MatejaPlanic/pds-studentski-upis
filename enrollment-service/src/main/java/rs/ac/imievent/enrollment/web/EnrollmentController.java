package rs.ac.imievent.enrollment.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rs.ac.imievent.enrollment.dto.*;
import rs.ac.imievent.enrollment.service.EnrollmentService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService service;

    @PostMapping
    public ResponseEntity<EnrollmentResponse> create(@Valid @RequestBody EnrollmentCreateRequest req,
                                                     UriComponentsBuilder uri) {
        var saved = service.create(req);
        URI location = uri.path("/api/enrollments/{id}").build(saved.id());
        return ResponseEntity.created(location).body(saved);
    }

    @GetMapping("/{id}")
    public EnrollmentResponse get(@PathVariable Long id) { return service.get(id); }

    @GetMapping
    public List<EnrollmentResponse> all() { return service.all(); }

    @GetMapping("/{id}/details")
    public EnrollmentDetails details(@PathVariable Long id) { return service.details(id); }

    @PutMapping("/{id}")
    public EnrollmentResponse update(@PathVariable Long id,
                                     @Valid @RequestBody EnrollmentUpdateRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // 204
    }
}
