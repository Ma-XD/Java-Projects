package expression.parser;

import expression.TripleExpression;
import expression.exceptions.ParseException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Parser<T> {
    TripleExpression<T> parse(String expression) throws ParseException;
}
