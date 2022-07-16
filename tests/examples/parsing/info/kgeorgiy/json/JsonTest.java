package info.kgeorgiy.json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
class JsonTest {
    private static void valid(final Object expected, final String json) {
        Assertions.assertEquals(expected, parse(json));
    }

    private static Object parse(final String json) {
        return Json.parse(json);
    }

    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    private static void invalid(final String input) {
        try {
            final Object value = parse(input);
            Assertions.fail("Expected fail, found " + value + " for " + input);
        } catch (final IllegalArgumentException e) {
            System.out.format("Expected error%n\t'%s'%n\t%s%n", input, e.getMessage());
        }
    }

    @Test
    void testString() {
        valid("", "\"\"");
        valid("abc", "\"abc\"");
        valid("abc", "    \"abc\"   ");
        // \"
        valid("\"", "\"\\\"\"");
        //\\\/\b\f\n\r\t
        valid("\\/\b\f\n\r\t", "\"\\\\\\/\\b\\f\\n\\r\\t\"");
        invalid("   \"abc");
    }

    @Test
    void testUnicodeEscape() {
        valid("\u1234", "\"\\u1234\"");
        valid("\uabef", "\"\\uabef\"");
        valid("\uabef", "\"\\uABEF\"");
        valid("\uabef0", "\"\\uABEF0\"");
        invalid("\"\\uABE\"");
    }

    @Test
    void testInvalidEscape() {
        invalid("\"\\q\"");
    }

    @Test
    void testWhitespaceString() {
        valid("hello", " \t   \r\n\"hello\"");
        valid("hello", "\"hello\" \t   \r\n");
    }

    @Test
    void testInvalidSuffix() {
        invalid("\"hello\" abc");
    }

    @Test
    void testEmptyInput() {
        invalid("");
        invalid("   ");
    }

    @Test
    void testNumbers() {
        assertNumber(1, "1");
        assertNumber(1, "1.0");
        assertNumber(12.3, "12.3");
        assertNumber(-12.3, "-12.3");
        assertNumber(3.14e10, "3.14e10");
        assertNumber(3.14E-10, "3.14E-10");
        assertNumber(3.14E+10, "3.14E+10");
    }

    @Test
    void testInvalidNumber() {
        invalid("1p1");
        invalid("--1");
        invalid("-+1");
    }

    @Test
    void testInvalidPlusNumber() {
        invalid("+1");
    }

    @Test
    void testInvalidOctalNumber() {
        invalid("070");
    }

    @Test
    void testInvalidNumberSequence() {
        invalid("1+1");
    }

    private static void assertNumber(final double expected, final String json) {
        Assertions.assertEquals(expected, ((Number) parse(json)).doubleValue(), 1e-10);
    }

    @Test
    void testBoolean() {
        valid(true, "true");
        valid(false, "false");
    }

    @Test
    void testNull() {
        valid(null, "null");
        invalid("nulll");
        invalid("nul");
        invalid("nulk");
    }

    @Test
    void testNumber() {
        invalid("number");
    }

    @Test
    void testArray() {
        valid(List.of(), "[]");
        valid(Collections.singletonList("hello"), "[\"hello\"]");
        valid(Arrays.asList("hello", "world"), "[\"hello\", \"world\"]");
    }

    @Test
    void testObject() {
        valid(Map.of(), "{}");
        valid(Map.of("hello", "world"), "{\"hello\": \"world\"}");
        valid(Map.of("hello", Boolean.TRUE), "{\"hello\": true}");
        final Map<String, Object> expected = Map.of(
                "key1", "value1",
                "key2", "value2",
                "key3", "value3"
        );
        valid(expected, "{\"key1\": \"value1\", \"key2\": \"value2\", \"key3\": \"value3\"}");
    }
}
