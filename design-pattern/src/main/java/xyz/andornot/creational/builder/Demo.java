package xyz.andornot.creational.builder;

public class Demo {
    public static void main(String[] args) {
        var director = new Director();
        var builder = new CarBuilder();
        director.constructSportsCar(builder);

        var car = builder.getResult();
        System.out.println("Car built:\n" + car.getCarType());

        var manualBuilder = new CarManualBuilder();
        director.constructSUV(manualBuilder);
        var carManual = manualBuilder.getResult();
        System.out.println("\nCar manual built:\n" + carManual.print());
    }
}
