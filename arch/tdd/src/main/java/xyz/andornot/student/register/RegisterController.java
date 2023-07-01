package xyz.andornot.student.register;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request) {
        try {
            registerService.execute(request);
            return ResponseEntity.ok(ApiResponse.empty());
        } catch (StudentNotExistException e) {
            log.info("Student not found. " + e.getMessage());
            return ResponseEntity.status(400).body(ApiResponse.bad(987));
        }
    }
}
