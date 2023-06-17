package xyz.andornot

import jakarta.annotation.Resource
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@SpringBootTest
@ContextConfiguration
class LoadContextTest extends Specification {
    @Resource
    private RedisTemplate<String, Object> redisTemplate

    def "when context is load then all expected beans are created"() {
        expect: "the RedisTemplate<String, Object> is created"
        redisTemplate
    }
}
