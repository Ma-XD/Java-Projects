package expression.types;

public interface TypeOperations<T extends Number> {
    T add(T first, T second);
    T sub(T first, T second);
    T mul(T first, T second);
    T div(T first, T second);
    T neg(T first);
    T cnst(String constant);
}
