/**
 * Immutable Cartesian vector with default {@link Object#equals(Object)} method.
 *
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Vector {
    public final int x;
    public final int y;

    public Vector(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "Vector(x = " + x + ", y = " + y + ")";
    }
}
