package xyz.andornot.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;

@RestController
public class StreamJsonController {
    @GetMapping(value = "sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Data> sse() {
        return dataStream();
    }

    @GetMapping(value = "ndjson", produces = MediaType.APPLICATION_NDJSON_VALUE)
    Flux<Data> ndjson() {
        return dataStream();
    }

    private Flux<Data> dataStream() {
        return Flux.interval(Duration.ofSeconds(1))
                .take(5)
                .map(i -> new Data(i, Instant.now()));
    }

    record Data(Long seqNo, Instant timestamp) {
    }
}
