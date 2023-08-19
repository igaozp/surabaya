package xyz.andornot;

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author igaozp
 * @since 2022/2/14
 */
@SpringBootApplication
@EnableRedisDocumentRepositories
public class RedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }
}
