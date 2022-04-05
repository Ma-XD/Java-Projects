package expression;

public class Variable<T> implements CommonExpression<T> {
    private final String value;

    public Variable(String value) {
        this.value = value;
    }

    @Override
    public T evaluate(T x, T y, T z) {
        switch (value) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                throw new ExpressionException("Illegal variable");
        }
    }

    @Override
    public String toString() {
        return value;
    }

}
