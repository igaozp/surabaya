package xyz.andornot.scholarship.findandnotify;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import xyz.andornot.scholarship.Transcript;
import xyz.andornot.scholarship.notify.SendResultEmailService;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

public class FindTopAndNotifyServiceTest {
    private final TranscriptRepository repository = Mockito.mock(TranscriptRepository.class);
    private final SendResultEmailService emailService = Mockito.mock(SendResultEmailService.class);
    private final FindTopAndNotifyService service = new FindTopAndNotifyService(repository, emailService);

    @Test
    void one_student() {
        given_highest_score_students("2021-fail", 9527L, transcript(55688L));

        when_execute_service("2021-fail", 9527L);

        then_send_email_like(55688L, 1);
    }

    @Test
    void many_students() {
        given_highest_score_students("2021-fail", 9527L, transcript(55688L), transcript(3345678L));

        when_execute_service("2021-fail", 9527L);

        then_send_email_like(55688L, 1);
        then_send_email_like(3345678L, 1);
    }

    @Test
    void NO_students() {
        given_highest_score_students("2021-fail", 9527L);

        when_execute_service("2021-fail", 9527L);

        then_NEVER_send_emails();
    }

    private void then_NEVER_send_emails() {
        Mockito.verify(emailService, Mockito.never()).send(anyLong(), anyString());
    }

    private void then_send_email_like(long studentId, int invokes) {
        Mockito.verify(emailService, Mockito.times(invokes)).send(studentId, "Congratulations! You've got Scholarship");
    }

    private void when_execute_service(String semester, long courseId) {
        service.execute(semester, courseId);
    }

    private Transcript transcript(long studentId) {
        return new Transcript(studentId);
    }

    private void given_highest_score_students(String semester, long courseId, Transcript... transcripts) {
        Mockito.when(repository.findHighestScore(semester, courseId)).thenReturn(Arrays.asList(transcripts));
    }
}
