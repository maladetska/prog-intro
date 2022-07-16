package info.kgeorgiy.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public final class Json {
    public static Object parse(final String source) {
        return parse(new StringSource(source));
    }

    public static Object parse(final CharSource source) {
        return new JsonParser(source).parseJson();
    }

    private static class JsonParser extends BaseParser {
        public JsonParser(final CharSource source) {
            super(source);
        }

        /*
         * json
         *    element
         */
        public Object parseJson() {
            final Object result = parseElement();
            if (eof()) {
                return result;
            }
            throw error("End of JSON expected");
        }

        /*
         * element
         *     ws value ws
         */
        private Object parseElement() {
            skipWhitespace();
            final Object result = parseValue();
            skipWhitespace();
            return result;
        }

        /*
         * value
         *    object
         *    array
         *    string
         *    number
         *    "true"
         *    "false"
         *    "null"
         */
        private Object parseValue() {
            if (take('{')) {
                return parseObject();
            } else if (take('[')) {
                return parseArray();
            } else if (take('"')) {
                return parseString();
            } else if (take('t')) {
                expect("rue");
                return true;
            } else if (take('f')) {
                expect("alse");
                return false;
            } else if (take('n')) {
                expect("ull");
                return null;
            } else {
                return parseNumber();
            }
        }

        /*
         * array
         *     '[' ws ']'
         *     '[' elements ']'
         *
         * elements
         *     element
         *     element ',' elements
         */
        private List<Object> parseArray() {
            skipWhitespace();
            if (take(']')) {
                return List.of();
            }

            final List<Object> array = new ArrayList<>();
            array.add(parseElement());
            while (take(',')) {
                array.add(parseElement());
            }

            expect(']');
            return array;
        }

        /*
         * object
         *     '{' ws '}'
         *     '{' members '}'
         */
        private Map<String, Object> parseObject() {
            skipWhitespace();
            if (take('}')) {
                return Map.of();
            }
            final Map<String, Object> object = new HashMap<>();
            addMember(object);

            while (take(',')) {
                addMember(object);
            }
            expect('}');
            return object;
        }

        /*
         * member
         *     ws string ws ':' element
         */
        private void addMember(final Map<String, Object> object) {
            skipWhitespace();
            expect('"');
            final String key = parseString();
            skipWhitespace();
            expect(':');
            final Object element = parseElement();
            object.put(key, element);
        }

        /*
         * string
         * 	  '"' characters '"'
         * characters
         *     ""
         *     character characters
         * character
         *     '0020' . '10FFFF' - '"' - '\'
         *     '\' escape
         * escape
         *     '"'
         *     '\'
         *     '/'
         *     'b'
         *     'f'
         *     'n'
         *     'r'
         *     't'
         *     'u' hex hex hex hex
         */
        private String parseString() {
            final StringBuilder sb = new StringBuilder();
            while (!take('"')) {
                if (eof()) {
                    throw error("Unterminated string");
                }
                if (take('\\')) {
                    if (
                            escaped(sb, '\"', '\"')
                                    || escaped(sb, '\\', '\\')
                                    || escaped(sb, '/', '/')
                                    || escaped(sb, '\b', 'b')
                                    || escaped(sb, '\f', 'f')
                                    || escaped(sb, '\n', 'n')
                                    || escaped(sb, '\r', 'r')
                                    || escaped(sb, '\t', 't')
                    ) {
                        // next char
                    } else if (take('u')) {
                        int value = 0;
                        for (int i = 0; i < 4; i++) {
                            value <<= 4;
                            if (between('0', '9')) {
                                value = nextHex(value, '0');
                            } else if (between('a', 'z')) {
                                value = nextHex(value, 'a' - 10);
                            } else if (between('A', 'Z')) {
                                value = nextHex(value, 'A' - 10);
                            } else {
                                throw error("Expected hex digit");
                            }
                        }
                        sb.append((char) value);
                    } else {
                        throw error("Unknown escape character \\" + take());
                    }
                } else {
                    sb.append(take());
                }
            }
            return sb.toString();
        }

        private int nextHex(int value, final int delta) {
            value += take() - delta;
            return value;
        }

        private boolean escaped(final StringBuilder sb, final char character, final char expected) {
            final boolean consumed = take(expected);
            if (consumed) {
                sb.append(character);
            }
            return consumed;
        }

        /*
         * number
         *     integer fraction exponent
         */
        private Object parseNumber() {
            final StringBuilder sb = new StringBuilder();
            takeInteger(sb);

            /*
             * fraction
             *     ""
             *     '.' digits
             */
            if (take('.')) {
                sb.append('.');
                takeDigits(sb);
            }

            /*
             * exponent
             *     ""
             *     'E' sign digits
             *     'e' sign digits
             * sign
             *     ""
             *     '+'
             *     '-'
             */
            if (take('e') || take('E')) {
                sb.append('e');
                if (take('+')) {
                    // Do nothing
                } else if (take('-')) {
                    sb.append('-');
                }
                takeDigits(sb);
            }

            try {
                return Double.parseDouble(sb.toString());
            } catch (final NumberFormatException e) {
                throw error("Invalid number " + sb);
            }
        }

        /*
         * digits
         *     digit
         *     digit digits
         *
         * digit
         *     '0'
         *     onenine
         */
        private void takeDigits(final StringBuilder sb) {
            while (between('0', '9')) {
                sb.append(take());
            }
        }

        /*
         * integer
         *     digit
         *     onenine digits
         *     '-' digit
         *     '-' onenine digits
         *
         * onenine
         *     '1' . '9'
         */
        private void takeInteger(final StringBuilder sb) {
            if (take('-')) {
                sb.append('-');
            }
            if (take('0')) {
                sb.append('0');
            } else if (between('1', '9')) {
                takeDigits(sb);
            } else {
                throw error("Invalid number");
            }
        }

        /*
         * ws
         *    ""
         *    '0020' ws
         *    '000A' ws
         *    '000D' ws
         *    '0009' ws
         */
        private void skipWhitespace() {
            while (take(' ') || take('\r') || take('\n') || take('\t')) {
                // skip
            }
        }
    }
}
