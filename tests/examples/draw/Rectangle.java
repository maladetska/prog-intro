package draw;

import java.awt.*;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Rectangle extends AbstractShape {
    private int width;
    private int height;

    public Rectangle(final Color color, final int x, final int y, final int width, final int height) {
        super(color, x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    protected void drawImpl(final Graphics g) {
        g.fillRect(x, y, width, height);
    }

    @Override
    public Rectangle copy() {
        return new Rectangle(color, x, y, width, height);
    }
}
