package expression.parser;

import expression.exceptions.ParseException;

public interface CharSource {
    boolean hasNext();
    char next();
    char prev();
    int getPos();
    ParseException error(final String message);
}
