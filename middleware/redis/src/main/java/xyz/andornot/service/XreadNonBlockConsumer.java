package xyz.andornot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.connection.stream.StreamReadOptions;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import xyz.andornot.domain.Person;

import java.time.Duration;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * xread 读取 stream 数据，读取后不会删除数据
 * 每次启动可重复读取之前的数据
 *
 * @author igaozp
 * @since 2022/2/16
 */
@Slf4j
//@Component
public class XreadNonBlockConsumer implements InitializingBean, DisposableBean {
    private ThreadPoolExecutor threadPoolExecutor;
    private final RedisTemplate<String, Object> redisTemplate;
    private volatile boolean stop = false;

    public XreadNonBlockConsumer(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void destroy() {
        stop = true;
        threadPoolExecutor.shutdown();
    }

    @Override
    public void afterPropertiesSet() {
        threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            thread.setName("xread-nonblock");
            return thread;
        });

        var streamReadOptions = StreamReadOptions.empty().block(Duration.ofSeconds(1)).count(10);

        var readOffset = new StringBuilder("0-0");
        threadPoolExecutor.execute(() -> {
            while (!stop) {
                var records = redisTemplate.opsForStream()
                        .read(Person.class, streamReadOptions, StreamOffset.create("STREAM_01", ReadOffset.from(readOffset.toString())));
                if (CollectionUtils.isEmpty(records)) {
                    log.warn("未读取到数据");
                    continue;
                }
                records.forEach(r -> {
                    log.info("读取到数据：{}", r);
                    readOffset.setLength(0);
                    readOffset.append(r.getId());
                });
            }
        });
    }
}
