package xyz.andornot.student.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterService {
    private final StudentRepository studentRepository;

    @Autowired
    public RegisterService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void execute(RegisterRequest request) throws StudentNotExistException {
        try {
            studentRepository.register(request);
        } catch (DataNotFoundException e) {
            throw new StudentNotExistException("Student not exists.", e);
        }
    }
}
