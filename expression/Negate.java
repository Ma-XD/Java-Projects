package expression;

import expression.types.TypeOperations;

public class Negate<T> extends AbstractUnaryExpression<T> implements CommonExpression<T> {
    public Negate(TypeOperations<T> type, CommonExpression<T> expression1) {
        super(type, expression1);
        operation = "-";
    }

    protected T calculate(T first) {
        return type.neg(first);
    }
}
