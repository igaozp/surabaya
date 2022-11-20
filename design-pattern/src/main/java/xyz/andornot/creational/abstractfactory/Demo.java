package xyz.andornot.creational.abstractfactory;

public class Demo {

    public static void main(String[] args) {
        var app = configureApplication();
        app.paint();
    }

    private static Application configureApplication() {
        GUIFactory factory;
        var osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("mac")) {
            factory = new MacOSFactory();
        } else {
            factory = new WindowsFactory();
        }
        return new Application(factory);
    }
}
