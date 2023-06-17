package xyz.andornot

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import spock.lang.Specification

import javax.annotation.Resource

@SpringBootTest(classes = RedisApplication)
class LoadContextTest extends Specification {
    @Resource
    private RedisTemplate<String, Object> redisTemplate

    def "when context is load then all expected beans are created"() {
        expect: "the RedisTemplate<String, Object> is created"
        redisTemplate
    }
}
