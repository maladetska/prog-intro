/**
 * Immutable Cartesian vector with default {@link Object#equals(Object)} method.
 *
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class VectorWithEquals {
    public final int x;
    public final int y;

    public VectorWithEquals(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "VectorWithEquals(x = " + x + ", y = " + y + ")";
    }

    @Override
    // Notice that equals method receives Object, the common ancestor of all reference classes.
    public boolean equals(final Object o) {
        // Check runtime object type
        if (o instanceof VectorWithEquals) {
            // Type matches
            // Cast the reference
            VectorWithEquals v = (VectorWithEquals) o;
            // Check actual equality
            return x == v.x && y == v.y;
        } else {
            // Unknown type
            return false;
        }
    }
}
