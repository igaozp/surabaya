package xyz.andornot.creational.builder;

import xyz.andornot.creational.builder.feature.*;

public class Manual {
    private final CarType carType;
    private final int seats;
    private final Engine engine;
    private final Transmission transmission;
    private final TripComputer tripComputer;
    private final GPSNavigator gpsNavigator;

    public Manual(CarType carType, int seats, Engine engine, Transmission transmission, TripComputer tripComputer, GPSNavigator gpsNavigator) {
        this.carType = carType;
        this.seats = seats;
        this.engine = engine;
        this.transmission = transmission;
        this.tripComputer = tripComputer;
        this.gpsNavigator = gpsNavigator;
    }

    public String print() {
        var info = "";
        info += "Type of car: " + carType + "\n";
        info += "Count of seats: " + seats + "\n";
        info += "Engine: volume: " + engine.getVolume() + "; mileage - " + engine.getMileage() + "\n";
        info += "Transmission: " + transmission + "\n";
        if (this.transmission != null) {
            info += "Trip Computer: Functional\n";
        } else {
            info += "Trip Computer: N/A\n";
        }
        if (this.gpsNavigator != null) {
            info += "GPS Navigator: Functional\n";
        } else {
            info += "GPS Navigator: N/A\n";
        }
        return info;
    }
}
