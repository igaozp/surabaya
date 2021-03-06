package xyz.andornot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import xyz.andornot.domain.Person;

import javax.annotation.Resource;

/**
 * @author igaozp
 * @since 2022/2/15
 */
@Slf4j
@Component
public class StreamProducer {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void sendRecord(String key) {
        var person = new Person("Alice", 20);
        var record = StreamRecords.newRecord()
                .in(key)
                .ofObject(person)
                .withId(RecordId.autoGenerate());
        var recordId = redisTemplate.opsForStream().add(record);
        log.info("send record: {}", recordId);
    }
}
