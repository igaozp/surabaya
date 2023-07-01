package xyz.andornot.scholarship.notify;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.*;

class SendResultEmailServiceSilentTest {
    @Test
    void silent_send() {
        var mailer = mock(Mailer.class);
        var es = mock(ExecutorService.class);

        var service = new SendResultEmailService(mailer, es);
        service.silentSend(
                List.of(
                        new ScholarshipResult(),
                        new ScholarshipResult(),
                        new ScholarshipResult()
                )
        );

        ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);
        verify(es, times(3)).submit(captor.capture());

        List<Runnable> runnableList = captor.getAllValues();
        runnableList.forEach(Runnable::run);

        verify(mailer, times(3)).silentSend(any(ScholarshipResult.class));
    }
}
