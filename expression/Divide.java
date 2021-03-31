package expression;

import expression.types.TypeOperations;

public class Divide<T> extends AbstractExpression<T> implements CommonExpression<T> {

    public Divide(TypeOperations<T> type, CommonExpression<T> expression1, CommonExpression<T> expression2) {
        super(type, expression1, expression2);
        operation = '/';
    }
    protected T calculate(T first, T second) {
        return type.div(first, second);
    }
}
