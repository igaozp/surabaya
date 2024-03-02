package xyz.andornot.behavioral.visitor;

public record Rectangle(int id, int x, int y, int width, int height) implements Shape {

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitRectangle(this);
    }

    @Override
    public void move(int x, int y) {
        // move shape
    }

    @Override
    public void draw() {
        // draw shape
    }
}
