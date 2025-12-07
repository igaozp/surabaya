package xyz.andornot.structural.flyweight;

import java.awt.*;

public record TreeType(String name, Color color, String otherTreeData) {

    public void draw(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillRect(x - 1, y, 3, 5);
        g.setColor(color);
        g.fillOval(x - 5, y - 10, 10, 10);
    }
}
