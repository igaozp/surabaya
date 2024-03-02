package xyz.andornot.structural.adapter;

public record SquarePeg(double width) {

    public double getSquare() {
        return Math.pow(this.width, 2);
    }
}
