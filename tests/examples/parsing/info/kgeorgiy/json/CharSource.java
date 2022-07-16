package info.kgeorgiy.json;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface CharSource {
    boolean hasNext();
    char next();
    IllegalArgumentException error(final String message);
}
