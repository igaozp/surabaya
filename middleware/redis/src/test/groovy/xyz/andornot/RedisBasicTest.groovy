package xyz.andornot

import jakarta.annotation.Resource
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@SpringBootTest
@ContextConfiguration
class RedisBasicTest extends Specification {
    @Resource
    private RedisTemplate<String, Object> redisTemplate

    def "redis basic write test"() {
        expect: "redis basic write OK"
        redisTemplate.opsForValue().set("key", "value")
    }

    def "redis basic read test"() {
        expect: "redis basic read OK"
        redisTemplate.opsForValue().get("key") == "value"
    }

//    def "clean"() {
//        expect: "clean OK"
//        redisTemplate.delete("key")
//    }
}
