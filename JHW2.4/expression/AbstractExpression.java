package expression;

import expression.types.TypeOperations;

public abstract class AbstractExpression<T extends Number> implements CommonExpression<T>{
    protected final CommonExpression<T> expression1, expression2;
    protected final TypeOperations<T> type;
    protected char operation;

    protected AbstractExpression(TypeOperations<T> type, CommonExpression<T> expression1, CommonExpression<T> expression2) {
        this.type = type;
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    protected abstract T calculate(T first, T second);

    @Override
    public T evaluate(T x, T y, T z) {
        return calculate(expression1.evaluate(x, y, z), expression2.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "(" + expression1.toString() +
                " " + operation + " " +
                expression2.toString() + ")";
    }
}
