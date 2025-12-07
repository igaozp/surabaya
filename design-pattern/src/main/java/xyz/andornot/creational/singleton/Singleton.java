package xyz.andornot.creational.singleton;

public record Singleton(String value) {
    private static Singleton instance;

    public Singleton {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Singleton getInstance(String value) {
        if (instance == null) {
            instance = new Singleton(value);
        }
        return instance;
    }
}
