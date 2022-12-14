package xyz.andornot.structural.adapter;

public class RoundHole {
    private final double radius;

    RoundHole(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public boolean fits(RoundPeg roundPeg) {
        return (this.getRadius() >= roundPeg.getRadius());
    }
}
