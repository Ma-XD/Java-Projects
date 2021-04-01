package expression.types;

import expression.ExpressionException;

public class IntegerOperations implements TypeOperations<Integer> {
    @Override
    public Integer add(Integer first, Integer second) {
        if (second > Integer.MAX_VALUE - first && first > 0 && second > 0 ||
                second < Integer.MIN_VALUE - first && first < 0 && second < 0) {
            throw new ExpressionException("Overflow");
        }
        return first + second;
    }

    @Override
    public Integer sub(Integer first, Integer second) {
        if (first < Integer.MIN_VALUE + second && first < 0 && second > 0 ||
                first > Integer.MAX_VALUE + second && first >= 0 && second < 0) {
            throw new ExpressionException("Overflow");
        }
        return first - second;
    }

    @Override
    public Integer mul(Integer first, Integer second) {
        if ( (second != -1 || first == Integer.MIN_VALUE) &&  ( first > 0 && second > 0 && first > Integer.MAX_VALUE / second ||
                first < 0 && second < 0 && first < Integer.MAX_VALUE / second ||
                first < 0 && second > 0 && first < Integer.MIN_VALUE / second ||
                first > 0 && second < 0 && first > Integer.MIN_VALUE / second )
        ) {
            throw new ExpressionException("Overflow");
        }
        return first * second;
    }

    @Override
    public Integer div(Integer first, Integer second) {
        if (second == 0 || first == Integer.MIN_VALUE && second == -1) {
            throw new ExpressionException("Division by zero");
        }
        return first / second;
    }

    @Override
    public Integer negate(Integer first) {
        if (first == Integer.MIN_VALUE) {
            throw new ExpressionException("Overflow");
        }
        return -first;
    }

    @Override
    public Integer cnst(String constant) {
        try {
            return Integer.parseInt(constant);
        } catch (NumberFormatException e) {
            throw new ExpressionException("Cannot cast \"" + constant + "\" to int");
        }

    }
}
