package xyz.andornot.creational.singleton;

public class DemoMultiThread {
    public static void main(String[] args) {
        System.out.println("""
                If you see the same value, then singleton was reused (yay!)
                If you see different values, then 2 singletons were created (booo!)
                RESULT:
                """);
        var threadFoo = new Thread(new ThreadFoo());
        var threadBar = new Thread(new ThreadBar());
        threadFoo.start();
        threadBar.start();

        var safeThreadFoo = new Thread(new SafeThreadFoo());
        var safeThreadBar = new Thread(new SafeThreadBar());
        safeThreadFoo.start();
        safeThreadBar.start();
    }

    static class ThreadFoo implements Runnable {
        @Override
        public void run() {
            var singleton = Singleton.getInstance("FOO");
            System.out.println(singleton.value);
        }
    }

    static class ThreadBar implements Runnable {
        @Override
        public void run() {
            var singleton = Singleton.getInstance("BAR");
            System.out.println(singleton.value);
        }
    }

    static class SafeThreadFoo implements Runnable {
        @Override
        public void run() {
            var singleton = SafeSingleton.getInstance("FOO");
            System.out.println("safe " + singleton.value);
        }
    }

    static class SafeThreadBar implements Runnable {
        @Override
        public void run() {
            var singleton = SafeSingleton.getInstance("BAR");
            System.out.println("safe " + singleton.value);
        }
    }
}
