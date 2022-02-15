package xyz.andornot.service;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author igaozp
 * @since 2022/2/15
 */
@Component
public class StreamMessageRunner implements ApplicationRunner {
    @Resource
    private StreamProducer streamProducer;

    @Override
    public void run(ApplicationArguments args) {
        Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(() -> streamProducer.sendRecord("STREAM_01"),
                        0, 5, TimeUnit.SECONDS);
    }
}
