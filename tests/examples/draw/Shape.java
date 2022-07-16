package draw;

import java.awt.*;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Shape {
    void draw(Graphics g);
    Color getColor();
    void setColor(Color color);
    void shift(int dx, int dy);
}
