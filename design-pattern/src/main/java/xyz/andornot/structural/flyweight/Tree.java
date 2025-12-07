package xyz.andornot.structural.flyweight;

import java.awt.*;

public record Tree(int x, int y, TreeType type) {

    public void draw(Graphics g) {
        type.draw(g, x, y);
    }
}
