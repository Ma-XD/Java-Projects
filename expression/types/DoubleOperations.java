package expression.types;

import expression.ExpressionException;

import java.math.BigInteger;

public class DoubleOperations implements TypeOperations<Double> {
    @Override
    public Double add(Double first, Double second) {
        return first + second;
    }

    @Override
    public Double sub(Double first, Double second) {
        return first - second;
    }

    @Override
    public Double mul(Double first, Double second) {
        return first * second;
    }

    @Override
    public Double div(Double first, Double second) {
        return first / second;
    }

    @Override
    public Double negate(Double first) {
        return -first;
    }

    @Override
    public Double cnst(String constant) {
        try {
            return Double.parseDouble(constant);
        } catch (NumberFormatException e) {
            throw new ExpressionException("Cannot cast \"" + constant + "\" to double");
        }
    }
}
