package xyz.andornot.creational.builder;

import xyz.andornot.creational.builder.feature.*;

public record Manual(CarType carType, int seats, Engine engine, Transmission transmission, TripComputer tripComputer,
                     GPSNavigator gpsNavigator) {

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
