package expression.exceptions;

public interface CharSource {
    boolean hasNext();
    char next();
    char prev();
    int getPos();
    ParseException error(final String message);
}
