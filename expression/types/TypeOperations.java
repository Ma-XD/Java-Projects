package expression.types;

public interface TypeOperations<T> {
    T add(T first, T second);
    T sub(T first, T second);
    T mul(T first, T second);
    T div(T first, T second);
    T negate(T first);
    T cnst(String constant);
}
