package xyz.andornot.structural.composite.editor;

import xyz.andornot.structural.composite.shapes.CompoundShape;
import xyz.andornot.structural.composite.shapes.Shape;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ImageEditor {
    private final EditorCanvas canvas;
    private final CompoundShape allShapes = new CompoundShape();

    public ImageEditor() {
        canvas = new EditorCanvas();
    }

    public void loadShapes(Shape... shapes) {
        allShapes.clear();
        allShapes.add(shapes);
        canvas.refresh();
    }

    private class EditorCanvas extends Canvas {
        private static final int PADDING = 10;
        JFrame frame;

        EditorCanvas() {
            createFrame();
        }

        void createFrame() {
            frame = new JFrame();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            JPanel contentPanel = new JPanel();
            Border padding = BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING);
            contentPanel.setBorder(padding);
            frame.setContentPane(contentPanel);

            frame.add(this);
            frame.setVisible(true);
            frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        }

        @Override
        public int getWidth() {
            return allShapes.getX() + allShapes.getWidth() + PADDING;
        }

        @Override
        public int getHeight() {
            return allShapes.getY() + allShapes.getHeight() + PADDING;
        }

        void refresh() {
            this.setSize(getWidth(), getHeight());
            frame.pack();
        }

        @Override
        public void paint(Graphics graphics) {
            allShapes.paint(graphics);
        }
    }
}
