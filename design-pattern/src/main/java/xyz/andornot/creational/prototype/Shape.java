package xyz.andornot.creational.prototype;

import java.util.Objects;

public abstract class Shape {
    protected int x;
    protected int y;
    protected String color;

    protected Shape() {
    }

    protected Shape(Shape target) {
        if (target != null) {
            this.x = target.x;
            this.y = target.y;
            this.color = target.color;
        }
    }

    public abstract Shape clone();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Shape shape = (Shape) o;

        if (x != shape.x) {
            return false;
        }
        if (y != shape.y) {
            return false;
        }
        return Objects.equals(color, shape.color);
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
