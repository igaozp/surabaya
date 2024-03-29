package xyz.andornot.behavioral.chainofresponsibility;

import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class Server {
    private final Map<String, String> users = new HashMap<>();
    @Setter
    private Middleware middleware;

    public boolean login(String email, String password) {
        if (middleware.check(email, password)) {
            System.out.println("Authorization have been successful!");
            return true;
        }
        return false;
    }

    public void register(String email, String password) {
        users.put(email, password);
    }

    public boolean hasEmail(String email) {
        return users.containsKey(email);
    }

    public boolean isValidPassword(String email, String password) {
        return users.get(email).equals(password);
    }
}
