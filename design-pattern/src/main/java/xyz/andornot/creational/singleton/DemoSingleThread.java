package xyz.andornot.creational.singleton;

public class DemoSingleThread {
    public static void main(String[] args) {
        System.out.println("""
                       If you see the same value, then singleton was reused (yay!)
                       If you see different values, then 2 singletons were created (booo!)
                       RESULT:
                """);
        var singleton = Singleton.getInstance("FOO");
        var anotherSingleton = Singleton.getInstance("BAR");
        System.out.println(singleton.value());
        System.out.println(anotherSingleton.value());
    }
}
