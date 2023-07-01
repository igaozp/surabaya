package xyz.andornot.scholarship.notify;

public interface Mailer {
    boolean send(ScholarshipResult result);

    void silentSend(ScholarshipResult result);
}
