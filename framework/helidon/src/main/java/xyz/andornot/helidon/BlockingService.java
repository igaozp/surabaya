package xyz.andornot.helidon;

import io.helidon.webclient.api.WebClient;
import io.helidon.webserver.http.HttpRules;
import io.helidon.webserver.http.HttpService;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BlockingService implements HttpService {
    private static WebClient client;

    public static void client(WebClient client) {
        BlockingService.client = client;
    }

    @Override
    public void routing(HttpRules rules) {
        rules.get("/one", this::one)
                .get("/sequence", this::sequence)
                .get("/parallel", this::parallel)
                .get("/sleep", this::sleep);
    }

    private static WebClient client() {
        if (client == null) {
            throw new RuntimeException("Client must be configured on BlockingService");
        }
        return client;
    }

    private static String callRemote(WebClient client) {
        return client.get().path("/remote").requestEntity(String.class);
    }

    private void sleep(ServerRequest req, ServerResponse res) throws InterruptedException {
        Thread.sleep(1000);
        res.send("finished");
    }

    private void one(ServerRequest req, ServerResponse res) {
        var response = callRemote(client());
        res.send(response);
    }

    private void sequence(ServerRequest req, ServerResponse res) {
        var count = count(req);
        var responses = new ArrayList<String>();

        for (int i = 0; i < count; i++) {
            responses.add(callRemote(client));
        }

        res.send("Combined results: " + responses);
    }

    private void parallel(ServerRequest req, ServerResponse res) throws ExecutionException, InterruptedException {
        try (var exec = Executors.newVirtualThreadPerTaskExecutor()) {
            var count = count(req);

            var futures = new ArrayList<Future<String>>();
            for (int i = 0; i < count; i++) {
                futures.add(exec.submit(() -> callRemote(client)));
            }

            var responses = new ArrayList<String>();
            for (var future : futures) {
                responses.add(future.get());
            }

            res.send("Combined results: " + responses);
        }
    }

    private int count(ServerRequest req) {
        return req.query().first("count").map(Integer::parseInt).orElse(3);
    }
}
