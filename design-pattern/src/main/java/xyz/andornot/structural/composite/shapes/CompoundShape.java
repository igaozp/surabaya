package xyz.andornot.structural.composite.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompoundShape extends BaseShape {
    protected List<Shape> children = new ArrayList<>();

    public CompoundShape(Shape... components) {
        super(0, 0, Color.BLACK);
        add(components);
    }

    public void add(Shape shape) {
        children.add(shape);
    }

    public void add(Shape... shapes) {
        Collections.addAll(children, shapes);
    }

    public void remove(Shape shape) {
        children.remove(shape);
    }

    public void remove(Shape... shapes) {
        children.removeAll(List.of(shapes));
    }

    public void clear() {
        children.clear();
    }

    @Override
    public int getX() {
        if (children.isEmpty()) {
            return 0;
        }
        var x = children.get(0).getX();
        for (Shape child : children) {
            x = Math.min(x, child.getX());
        }
        return x;
    }

    @Override
    public int getY() {
        if (children.isEmpty()) {
            return 0;
        }
        var y = children.get(0).getY();
        for (Shape child : children) {
            y = Math.min(y, child.getY());
        }
        return y;
    }

    @Override
    public int getWidth() {
        var maxWidth = 0;
        var x = getX();
        for (Shape child : children) {
            maxWidth = Math.max(maxWidth, child.getX() - x + child.getWidth());
        }
        return maxWidth;
    }

    @Override
    public int getHeight() {
        var maxHeight = 0;
        var y = getY();
        for (Shape child : children) {
            maxHeight = Math.max(maxHeight, child.getY() - y + child.getHeight());
        }
        return maxHeight;
    }

    @Override
    public void move(int x, int y) {
        for (Shape child : children) {
            child.move(x, y);
        }
    }

    @Override
    public boolean isInsideBounds(int x, int y) {
        for (Shape child : children) {
            if (child.isInsideBounds(x, y)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void unSelect() {
        super.unSelect();
        for (Shape child : children) {
            child.unSelect();
        }
    }

    public boolean selectChildAt(int x, int y) {
        for (Shape child : children) {
            if (child.isInsideBounds(x, y)) {
                child.select();
                return true;
            }
        }
        return false;
    }

    @Override
    public void paint(Graphics graphics) {
        if (isSelected()) {
            enableSelectionStyle(graphics);
            graphics.drawRect(getX() - 1, getY() - 1, getWidth() + 1, getHeight() + 1);
            disableSelectionStyle(graphics);
        }

        for (Shape child : children) {
            child.paint(graphics);
        }
    }
}
