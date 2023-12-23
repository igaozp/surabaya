package xyz.andornot.student.register;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(RegisterController.class)
public class RegisterControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RegisterService service;

    @Test
    void all_ok() throws Exception {
        mockMvc.perform(request("/register", 35L))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    @Test
    void student_not_found() throws Exception {
        given_student_not_exists(35L);

        mockMvc.perform(request("/register", 35L))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().json(bad_response(987)));
    }

    private void given_student_not_exists(long studentId) throws StudentNotExistException {
        doThrow(new StudentNotExistException("ANY_MESSAGE"))
                .when(service)
                .execute(new RegisterRequest(studentId));
    }

    private String bad_response(int code) throws JsonProcessingException {
        return objectMapper.writeValueAsString(ApiResponse.bad(code));
    }

    private MockHttpServletRequestBuilder request(String url, long studentId) throws JsonProcessingException {
        return MockMvcRequestBuilders
                .post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new RegisterRequest(studentId)));
    }
}
