package xyz.andornot.helidon;

import io.helidon.nima.testing.junit5.webserver.DirectClient;
import io.helidon.nima.testing.junit5.webserver.RoutingTest;

@RoutingTest
public class BlockingServiceTest extends AbstractBlockingServiceTest {
    BlockingServiceTest(DirectClient client) {
        super(client);
    }
}
