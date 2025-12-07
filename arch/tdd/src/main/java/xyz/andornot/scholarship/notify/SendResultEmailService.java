package xyz.andornot.scholarship.notify;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public record SendResultEmailService(Mailer mailer, ExecutorService executorService) {

    public SendResultEmailService(Mailer mailer) {
        this(mailer, Executors.newFixedThreadPool(300));
    }

    public List<Future<Boolean>> send(List<ScholarshipResult> results) {
        List<Future<Boolean>> futures = new ArrayList<>();

        for (ScholarshipResult result : results) {
            futures.add(executorService.submit(() -> mailer.send(result)));
        }

        return futures;
    }

    public void silentSend(List<ScholarshipResult> results) {
        for (ScholarshipResult result : results) {
            executorService.submit(() -> mailer.silentSend(result));
        }
    }

    public void send(long studentId, String message) {
        // TBD
    }
}
