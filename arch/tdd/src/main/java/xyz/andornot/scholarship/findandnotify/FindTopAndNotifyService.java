package xyz.andornot.scholarship.findandnotify;

import xyz.andornot.scholarship.Transcript;
import xyz.andornot.scholarship.notify.SendResultEmailService;

import java.util.List;

public class FindTopAndNotifyService {
    private final TranscriptRepository transcriptRepository;
    private final SendResultEmailService sendResultEmailService;

    public FindTopAndNotifyService(TranscriptRepository transcriptRepository, SendResultEmailService sendResultEmailService) {
        this.transcriptRepository = transcriptRepository;
        this.sendResultEmailService = sendResultEmailService;
    }

    public void execute(String semester, long courseId) {
        List<Transcript> transcripts = transcriptRepository.findHighestScore(semester, courseId);

        for (Transcript transcript : transcripts) {
            long studentId = transcript.getStudentId();
            this.sendResultEmailService.send(studentId, "Congratulations! You've got Scholarship");
        }
    }
}
