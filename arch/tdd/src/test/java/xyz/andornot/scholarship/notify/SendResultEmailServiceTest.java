package xyz.andornot.scholarship.notify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.mockito.Mockito.*;

class SendResultEmailServiceTest {
    @Test
    void when_send_returns_future() throws ExecutionException, InterruptedException {
        Mailer mailer = mock(Mailer.class);
        SendResultEmailService service = new SendResultEmailService(mailer);

        when(mailer.send(any(ScholarshipResult.class))).thenReturn(true, true, false);

        List<Future<Boolean>> futures = service.send(
                List.of(
                        new ScholarshipResult(),
                        new ScholarshipResult(),
                        new ScholarshipResult()
                )
        );

        int goods = 0;
        int bads = 0;
        for (Future<Boolean> future : futures) {
            if (future.get()) {
                goods++;
            } else {
                bads++;
            }
        }

        Assertions.assertEquals(2, goods);
        Assertions.assertEquals(1, bads);
    }
}
