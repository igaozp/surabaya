package xyz.andornot.helidon;

import io.helidon.webclient.api.WebClient;
import io.helidon.webserver.testing.junit5.RoutingTest;

@RoutingTest
public class BlockingServiceTest extends AbstractBlockingServiceTest {
    BlockingServiceTest(WebClient client) {
        super(client);
    }
}
