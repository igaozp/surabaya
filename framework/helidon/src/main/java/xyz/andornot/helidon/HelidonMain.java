package xyz.andornot.helidon;

import io.helidon.http.HeaderName;
import io.helidon.http.HeaderNames;
import io.helidon.http.InternalServerException;
import io.helidon.webclient.api.WebClient;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.HttpRouting;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class HelidonMain {
    private static final HeaderName SERVER = HeaderNames.create("Helidon");
    private static final AtomicInteger COUNTER = new AtomicInteger();

    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        var ws = WebServer.builder().routing(HelidonMain::routing).build().start();

        BlockingService.client(WebClient.builder().baseUri(STR."http://localhost:\{ws.port()}").build());
    }

    public static void routing(HttpRouting.Builder rules) {
        rules.addFilter(((chain, _, res) -> {
                    res.header(SERVER, "Helidon");
                    chain.proceed();
                })).get("/remote", HelidonMain::remote)
                .register("/", new BlockingService());
    }

    private static void remote(ServerRequest req, ServerResponse res) {
        var sleepMillis = RANDOM.nextInt(500);
        var counter = COUNTER.incrementAndGet();

        try {
            TimeUnit.MILLISECONDS.sleep(sleepMillis);
        } catch (InterruptedException e) {
            throw new InternalServerException("Failed to sleep", e);
        }
        res.send(STR."remote_\{counter}");
    }
}