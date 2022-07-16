package draw;

import java.awt.*;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Line extends AbstractShape {
    private int x2;
    private int y2;

    public Line(final Color color, final int x1, final int y1, final int x2, final int y2) {
        super(color, x1, y1);
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    protected void drawImpl(final Graphics g) {
        g.drawLine(x, y, x2, y2);
    }

    @Override
    public void shift(final int dx, final int dy) {
        super.shift(dx, dy);
        x2 += dx;
        y2 += dy;
    }

    @Override
    public Line copy() {
        return new Line(color, x, y, x2, y2);
    }
}
