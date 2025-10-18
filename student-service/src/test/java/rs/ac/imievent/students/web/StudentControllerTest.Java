package rs.ac.imievent.students.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import rs.ac.imievent.students.dto.StudentCreateRequest;
import rs.ac.imievent.students.dto.StudentResponse;
import rs.ac.imievent.students.service.StudentService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = StudentController.class)
class StudentControllerTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper om;

    @MockBean StudentService service;

    @Test
    void create_returns201_andLocation() throws Exception {
        var req = new StudentCreateRequest("Ana", "Anić", "ana@example.com", "IM1/2023", 2);
        var saved = new StudentResponse(1L, "Ana", "Anić", "ana@example.com", "IM1/2023", 2);

        Mockito.when(service.create(req)).thenReturn(saved);

        mvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/students/1"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }
}
