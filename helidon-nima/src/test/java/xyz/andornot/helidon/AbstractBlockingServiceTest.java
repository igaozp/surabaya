package xyz.andornot.helidon;

import io.helidon.common.http.Http;
import io.helidon.nima.testing.junit5.webserver.SetUpRoute;
import io.helidon.nima.webclient.http1.Http1Client;
import io.helidon.nima.webclient.http1.Http1ClientResponse;
import io.helidon.nima.webserver.http.HttpRouting;
import org.junit.jupiter.api.Test;
import xyz.andornot.helidon.nima.BlockingService;
import xyz.andornot.helidon.nima.NimaMain;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

abstract class AbstractBlockingServiceTest {
    private final Http1Client client;

    protected AbstractBlockingServiceTest(Http1Client client) {
        this.client = client;
        BlockingService.client(client);
    }

    @SetUpRoute
    static void routing(HttpRouting.Builder builder) {
        NimaMain.routing(builder);
    }

    @Test
    void testOne() {
        try (var response = client.get("/one").request()) {
            assertThat(response.status(), is(Http.Status.OK_200));
            var entity = response.as(String.class);
            assertThat(entity, startsWith("remote_"));
        }
    }

    @Test
    void testSequence() {
        try (var response = client.get("/sequence").request()) {
            assertThat(response.status(), is(Http.Status.OK_200));
            var entity = response.as(String.class);
            var results = splitAndValidateEntity(entity);
            validateUnique(results);
            validateSequence(results);
            validateOrdered(results);
        }
    }

    @Test
    void testSequenceParam() {
        try (var response = client.get("/sequence").queryParam("count", "1").request()) {
            assertThat(response.status(), is(Http.Status.OK_200));
            var entity = response.as(String.class);
            var results = splitAndValidateEntity(entity);
            validateUnique(results);
            validateSequence(results);
            validateOrdered(results);
        }
    }

    @Test
    void testParallel() {
        try (var response = client.get("/parallel").request()) {
            assertThat(response.status(), is(Http.Status.OK_200));
            var entity = response.as(String.class);
            var results = splitAndValidateEntity(entity);
            validateUnique(results);
            validateSequence(results);
        }
    }

    @Test
    void testParallelParam() {
        try (var response = client.get("/parallel").queryParam("count", "11").request()) {
            assertThat(response.status(), is(Http.Status.OK_200));
            var entity = response.as(String.class);
            var results = splitAndValidateEntity(entity);
            validateUnique(results);
            validateSequence(results);
        }
    }

    private void validateOrdered(int[] results) {
        var copy = Arrays.copyOf(results, results.length);
        Arrays.sort(copy);
        assertThat("Result should be sequential and ordered", results, is(copy));
    }

    private void validateSequence(int[] results) {
        Arrays.sort(results);
        var last = -1;
        for (int result : results) {
            assertThat(result, not(-1));
            if (last != -1) {
                assertThat("Results should be a sequence of numbers, but the sequence is missing a number: " + Arrays.toString(results),
                        result - last, is(1));
            }
            last = result;
        }
    }

    private void validateUnique(int[] results) {
        assertThat("Results should be distinct: " + Arrays.toString(results), results.length, is((int) Arrays.stream(results).boxed().count()));
    }

    private int[] splitAndValidateEntity(String entity) {
        var prefix = "Combined results: [";
        assertThat(entity, startsWith("Combined results: ["));
        assertThat(entity, endsWith("]"));
        var remotes = entity.substring(prefix.length(), entity.length() - 1);
        System.out.println(remotes);
        var splitRemotes = remotes.split(", ");
        var result = new int[splitRemotes.length];
        for (int i = 0; i < splitRemotes.length; i++) {
            var splitRemote = splitRemotes[i];
            assertThat(splitRemote, startsWith("remote_"));
            result[i] = Integer.parseInt(splitRemote.substring("remote_".length()));
        }
        return result;
    }
}
