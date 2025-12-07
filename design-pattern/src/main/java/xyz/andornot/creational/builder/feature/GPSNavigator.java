package xyz.andornot.creational.builder.feature;

public record GPSNavigator(String route) {
    public GPSNavigator() {
        this("221b, Baker Street, London to Scotland Yard, 8-10 Broadway, London");
    }

}
