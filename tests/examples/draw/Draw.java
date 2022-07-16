package draw;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Draw {
    public static void main(String[] args) {
        final int width = 1000;
        final int height = 500;

        final BufferedImage image = ImageUtil.createImage(width, height);

        final Graphics g = image.getGraphics();

        final int steps = 10;
        final int dx = width / steps / 2;
        final int dy = height / steps / 2;

        List<AbstractShape> shapes = new ArrayList<>();
        for (int i = steps; i > 0; i--) {
            shapes.add(new Rectangle(
                    i % 2 == 0 ? Color.LIGHT_GRAY : Color.GREEN,
                    width / 2 - i * dx, height / 2 - i * dy,
                    2 * i * dx, 2 * i * dy
            ));
            shapes.add(new Circle(
                    i % 2 == 0 ? Color.BLUE : Color.MAGENTA,
                    width / 2, height / 2,
                    i * dy
            ));
        }

        for (int i = 0; i < steps + 1; i++) {
            shapes.add(new Line(
                    Color.WHITE,
                    2 * i * dx, 0,
                    width - 2 * i * dx, height
            ));
        }


        for (Shape shape : shapes) {
            shape.draw(g);
        }

        for (Shape shape : shapes) {
            shape.shift(width / 2, height / 2);
            shape.setColor(ImageUtil.toGrayScale(shape.getColor()));
        }

        for (Shape shape : shapes) {
            shape.draw(g);
        }

        for (AbstractShape shape : shapes) {
            shape.copy().draw(g);
        }

        try {
            ImageUtil.writeImage("draw.png", image);
        } catch (IOException e) {
            System.out.println("Error saving image: " + e.getMessage());
        }

        ImageUtil.showImage(image);
    }
}
