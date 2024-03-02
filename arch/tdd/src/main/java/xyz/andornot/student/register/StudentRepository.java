package xyz.andornot.student.register;

import org.springframework.stereotype.Component;
import xyz.andornot.student.Student;

import java.util.Optional;

@Component
public class StudentRepository {
    public void register(RegisterRequest request) throws DataNotFoundException {
        // do nothing
    }

    public Optional<Student> find(long student) {
        return Optional.empty();
    }
}
