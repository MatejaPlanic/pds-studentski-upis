package rs.ac.imievent.enrollment.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.imievent.enrollment.client.StudentClient;
import rs.ac.imievent.enrollment.dto.EnrollmentCreateRequest;
import rs.ac.imievent.enrollment.repo.EnrollmentRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class EnrollmentServiceCrudTest {

    @Autowired EnrollmentService service;
    @Autowired EnrollmentRepository repo;

    @MockBean StudentClient studentClient;

    @Test
    void create_and_delete_enrollment() {
        // arrange
        var req = new EnrollmentCreateRequest(
                123L,
                "IM-101",
                "Matematika 1"
        );

        var created = service.create(req);

        assertThat(created.id()).isNotNull();
        assertThat(repo.existsById(created.id())).isTrue();

        service.delete(created.id());

        assertThat(repo.findById(created.id())).isEmpty();
    }
}
