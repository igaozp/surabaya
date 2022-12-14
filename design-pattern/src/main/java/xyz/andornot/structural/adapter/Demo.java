package xyz.andornot.structural.adapter;

public class Demo {
    public static void main(String[] args) {
        var hole = new RoundHole(5.0);
        var roundPeg = new RoundPeg(5.0);

        if (hole.fits(roundPeg)) {
            System.out.println("Round peg r5 fits round hole r5.");
        }

        var smallSqPeg = new SquarePeg(2);
        var largeSqPeg = new SquarePeg(20);

        var smallSqPegAdapter = new SquarePegAdapter(smallSqPeg);
        var largeSqPegAdapter = new SquarePegAdapter(largeSqPeg);
        if (hole.fits(smallSqPegAdapter)) {
            System.out.println("Square peg w2 fits round hole r5.");
        }
        if (!hole.fits(largeSqPegAdapter)) {
            System.out.println("Square peg w20 does not fit into round hole r5.");
        }
    }
}
