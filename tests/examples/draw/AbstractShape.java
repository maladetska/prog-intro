package draw;

import java.awt.*;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public abstract class AbstractShape
        /*extends Object*/
        implements Shape, Copyable
{
    protected Color color;
    protected int x;
    protected int y;

    protected AbstractShape(final Color color, final int x, final int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(final Color color) {
        this.color = color;
    }

    @Override
    public void shift(final int dx, final int dy) {
        x += dx;
        y += dy;
    }

    @Override
    public void draw(final Graphics g) {
        final Color oldColor = g.getColor();
        g.setColor(this.color);

        drawImpl(g);

        g.setColor(oldColor);
    }

    protected abstract void drawImpl(Graphics g);

    @Override
    public abstract AbstractShape copy();
}
