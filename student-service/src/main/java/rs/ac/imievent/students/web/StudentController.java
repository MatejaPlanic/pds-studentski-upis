package rs.ac.imievent.students.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import rs.ac.imievent.students.dto.StudentCreateRequest;
import rs.ac.imievent.students.dto.StudentResponse;
import rs.ac.imievent.students.service.StudentService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @PostMapping
    public ResponseEntity<StudentResponse> create(@Valid @RequestBody StudentCreateRequest req,
                                                  UriComponentsBuilder uri) {
        StudentResponse saved = service.create(req);
        URI location = uri.path("/api/students/{id}").build(saved.id());
        return ResponseEntity.created(location).body(saved); // 201
    }

    @GetMapping("/{id}")
    public StudentResponse get(@PathVariable Long id) { return service.get(id); }

    @GetMapping
    public List<StudentResponse> all() { return service.all(); }

    @PutMapping("/{id}")
    public StudentResponse update(@PathVariable Long id, @Valid @RequestBody StudentCreateRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
