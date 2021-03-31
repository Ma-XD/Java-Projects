package expression;

import expression.types.TypeOperations;

public class Const<T>  implements CommonExpression<T> {
    private final String constant;
    private final TypeOperations<T> type;

    public Const(TypeOperations<T> type, String constant) {
        this.constant = constant;
        this.type = type;
    }
    @Override
    public T evaluate(T x, T y, T z) {
        return type.cnst(constant);
    }

    @Override
    public String toString() {
        return constant;
    }
}
