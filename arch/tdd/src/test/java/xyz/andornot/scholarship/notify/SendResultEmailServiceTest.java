package xyz.andornot.scholarship.notify;

import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SendResultEmailServiceTest {
    private final Mailer mailer = mock(Mailer.class);
    private List<Future<Boolean>> futures;
    private final SendResultEmailService service = new SendResultEmailService(mailer);

    @Test
    void when_send_returns_future() throws ExecutionException, InterruptedException {
        when(mailer.send(any(ScholarshipResult.class))).thenReturn(true, true, false);

        futures = service.send(
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

        assertEquals(2, goods);
        assertEquals(1, bads);
    }

    @Test
    void when_send_returns_future_refactor_the_test() throws ExecutionException, InterruptedException {
        assume_mailer_execution_result_would_be(true, true, false);

        when_send_with_results(3);

        then_counts_in_futures_will_be(true, 2);
        then_counts_in_futures_will_be(false, 1);
    }

    private void assume_mailer_execution_result_would_be(boolean first, boolean... followings) {
        OngoingStubbing<Boolean> stubbing = when(mailer.send(any(ScholarshipResult.class))).thenReturn(first);

        for (boolean expected : followings) {
            stubbing.thenReturn(expected);
        }
    }

    private void when_send_with_results(int number) {
        futures = this.service.send(results(number));
    }

    private List<ScholarshipResult> results(int numbers) {
        var results = new ArrayList<ScholarshipResult>(numbers);
        for (int i = 0; i < numbers; i++) {
            results.add(new ScholarshipResult());
        }
        return results;
    }

    private void then_counts_in_futures_will_be(boolean target, int expected) throws ExecutionException, InterruptedException {
        assertEquals(expected, count_in_futures(target));
    }

    private int count_in_futures(boolean target) throws ExecutionException, InterruptedException {
        var counts = 0;
        for (Future<Boolean> future : futures) {
            if (future.get() == target) {
                counts++;
            }
        }
        return counts;
    }
}
