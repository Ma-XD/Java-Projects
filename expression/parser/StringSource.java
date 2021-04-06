package expression.parser;

import expression.exceptions.ParseException;
import expression.parser.CharSource;

public class StringSource implements CharSource {
    private final String data;
    private int pos;

    public StringSource(final String data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    @Override
    public char next() {
        return data.charAt(pos++);
    }

    @Override
    public char prev() {
        return data.charAt(--pos);
    }

    public int getPos() {
        return pos;
    }

    @Override
    public ParseException error(final String message) {
        return new ParseException(pos + ": " + message);
    }
}
