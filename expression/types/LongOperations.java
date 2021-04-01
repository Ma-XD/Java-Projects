package expression.types;

import expression.ExpressionException;

public class LongOperations implements TypeOperations<Long> {
    @Override
    public Long add(Long first, Long second) {
        return first + second;
    }

    @Override
    public Long sub(Long first, Long second) {
        return first - second;
    }

    @Override
    public Long mul(Long first, Long second) {
        return first * second;
    }

    @Override
    public Long div(Long first, Long second) {
        if (second == 0) {
            throw new ExpressionException("Division by zero");
        }
        return first / second;
    }

    @Override
    public Long negate(Long first) {
        return -first;
    }

    @Override
    public Long cnst(String constant) {
        try {
            return Long.parseLong(constant);
        } catch (NumberFormatException e) {
            throw new ExpressionException("Cannot cast \"" + constant + "\" to long");
        }
    }
}
