package xyz.andornot.structural.adapter;

public record RoundHole(double radius) {

    public boolean fits(RoundPeg roundPeg) {
        return (this.radius() >= roundPeg.getRadius());
    }
}
