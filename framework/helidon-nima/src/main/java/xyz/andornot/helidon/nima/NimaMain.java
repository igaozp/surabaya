package xyz.andornot.helidon.nima;

import io.helidon.common.http.Http;
import io.helidon.common.http.InternalServerException;
import io.helidon.nima.webclient.http1.Http1Client;
import io.helidon.nima.webserver.WebServer;
import io.helidon.nima.webserver.http.HttpRouting;
import io.helidon.nima.webserver.http.ServerRequest;
import io.helidon.nima.webserver.http.ServerResponse;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class NimaMain {
    private static final Http.HeaderValue SERVER = Http.Header.create(Http.Header.SERVER, "Nima");

    private static final AtomicInteger COUNTER = new AtomicInteger();

    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        var ws = WebServer.builder().routing(NimaMain::routing).start();

        BlockingService.client(Http1Client.builder().baseUri("http://localhost:" + ws.port()).build());
    }

    public static void routing(HttpRouting.Builder rules) {
        rules.addFilter(((chain, req, res) -> {
            res.header(SERVER);
            chain.proceed();
        })).get("/remote", NimaMain::remote)
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
        res.send("remote_" + counter);
    }
}