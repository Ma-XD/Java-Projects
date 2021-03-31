package expression.types;

import expression.ExpressionException;

import java.math.BigInteger;

public class BigIntegerOperations implements TypeOperations<BigInteger> {
    @Override
    public BigInteger add(BigInteger first, BigInteger second) {
        return first.add(second);
    }

    @Override
    public BigInteger sub(BigInteger first, BigInteger second) {
        return first.subtract(second);
    }

    @Override
    public BigInteger mul(BigInteger first, BigInteger second) {
        return first.multiply(second);
    }

    @Override
    public BigInteger div(BigInteger first, BigInteger second) {
        if (second.equals(new BigInteger(Integer.toString(0)))) {
            throw new ExpressionException("Division by zero");
        }
        return first.divide(second);
    }

    @Override
    public BigInteger negate(BigInteger first) {
        return first.negate();
    }

    @Override
    public BigInteger cnst(String constant) {
        return new BigInteger(constant);
    }
}
