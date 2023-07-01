package xyz.andornot.student.register;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;

class RegisterServiceTest {
    private final StudentRepository repository = Mockito.mock(StudentRepository.class);

    @Test
    void when_student_not_exists() throws DataNotFoundException {
        given_student_NOT_exists(35L);

        try {
            create_register_service().execute(request(35L));
            fail("should throw exception");
        } catch (StudentNotExistException e) {
            assertThat(e).hasMessageContaining("not exists");
        }
    }

    private void given_student_NOT_exists(long studentId) throws DataNotFoundException {
        Mockito.doThrow(new DataNotFoundException())
                .when(repository).register(request(studentId));
    }

    private RegisterRequest request(long studentId) {
        return new RegisterRequest(studentId);
    }

    private RegisterService create_register_service() {
        return new RegisterService(repository);
    }

    @Test
    void when_student_not_exists_alternative() throws DataNotFoundException {
        given_student_NOT_exists(35L);

        assertThatThrownBy(() -> create_register_service().execute(request(35L)))
                .hasMessageContaining("not exists");
    }
}
