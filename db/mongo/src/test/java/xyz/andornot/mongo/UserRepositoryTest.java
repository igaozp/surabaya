package xyz.andornot.mongo;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.andornot.mongo.domain.User;
import xyz.andornot.mongo.repo.UserRepository;

@SpringBootTest
public class UserRepositoryTest {
    @Resource
    private UserRepository userRepository;

    @Test
    public void insert() {
        var user = new User("Joe", 22);
        userRepository.insert(user);
    }
}
