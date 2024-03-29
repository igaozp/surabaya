package xyz.andornot.behavioral.chainofresponsibility;

public class ThrottlingMiddleware extends Middleware {
    private final int requestPerMinutes;
    private int request;
    private long currentTime;

    public ThrottlingMiddleware(int requestPerMinutes) {
        this.requestPerMinutes = requestPerMinutes;
        this.currentTime = System.currentTimeMillis();
    }

    @Override
    public boolean check(String email, String password) {
        if (System.currentTimeMillis() > currentTime + 60_000) {
            request = 0;
            currentTime = System.currentTimeMillis();
        }

        request++;

        if (request > requestPerMinutes) {
            System.out.println("Request limit exceeded");
            Thread.currentThread().interrupt();
        }
        return checkNext(email, password);
    }
}
