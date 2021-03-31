package expression;
import expression.types.TypeOperations;

public abstract class AbstractUnaryExpression<T> implements CommonExpression<T>{
    protected final CommonExpression<T> expression1;
    protected final TypeOperations<T> type;
    protected String operation;

    protected AbstractUnaryExpression(TypeOperations<T> type, CommonExpression<T> expression1) {
        this.type = type;
        this.expression1 = expression1;
    }

    protected abstract T calculate(T first);

    @Override
    public T evaluate(T x, T y, T z) {
        return calculate(expression1.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "(" + operation + " " +
                expression1.toString() + ")";
    }
}
