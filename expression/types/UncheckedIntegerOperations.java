package expression.types;

import expression.ExpressionException;

public class UncheckedIntegerOperations implements TypeOperations<Integer> {
    @Override
    public Integer add(Integer first, Integer second) {
        return first + second;
    }

    @Override
    public Integer sub(Integer first, Integer second) {
        return first - second;
    }

    @Override
    public Integer mul(Integer first, Integer second) {
        return first * second;
    }

    @Override
    public Integer div(Integer first, Integer second) {
        if (second == 0) {
            throw new ExpressionException("Division by zero");
        }
        return first / second;
    }

    @Override
    public Integer negate(Integer first) {
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
