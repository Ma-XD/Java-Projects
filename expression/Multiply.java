package expression;

import expression.types.TypeOperations;

public class Multiply<T> extends AbstractExpression<T> implements CommonExpression<T> {

    public Multiply(TypeOperations<T> type, CommonExpression<T> expression1, CommonExpression<T> expression2) {
        super(type, expression1, expression2);
        operation = '*';
    }

    protected T calculate(T first, T second) {
        return type.mul(first, second);
    }
}
