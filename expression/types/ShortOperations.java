package expression.types;

import expression.ExpressionException;

public class ShortOperations implements TypeOperations<Short> {
    @Override
    public Short add(Short first, Short second) {
        return (short)(first + second);
    }

    @Override
    public Short sub(Short first, Short second) {
        return (short)(first - second);
    }

    @Override
    public Short mul(Short first, Short second) {
        return (short)(first * second);
    }

    @Override
    public Short div(Short first, Short second) {
        if (second == 0) {
            throw new ExpressionException("Division by zero");
        }
        return (short)(first / second);
    }

    @Override
    public Short negate(Short first) {
        return (short)(-first);
    }

    @Override
    public Short cnst(String constant) {
        return (short) Integer.parseInt(constant);
    }
}
