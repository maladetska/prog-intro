/**
 * Immutable Cartesian vector with default {@link Object#equals(Object)} method.
 *
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class VectorWithHashCode {
    public final int x;
    public final int y;

    public VectorWithHashCode(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "VectorWithHashCode(x = " + x + ", y = " + y + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o instanceof VectorWithHashCode) {
            VectorWithHashCode v = (VectorWithHashCode) o;
            return x == v.x && y == v.y;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return x * 31 + ~y;
    }
}
