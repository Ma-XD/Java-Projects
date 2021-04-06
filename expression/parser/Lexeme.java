package expression.parser;

public enum Lexeme {
    ADD(10, "+"), SUB(10, "-"),
    MUL(20, "*"), DIV(20, "/"),
    ABS(40, "abs"), SQRT(40, "sqrt"),
    NEGATE(40, "-"),
    OPENED(40, "("), CLOSED(40, ")"),
    CONST(40, null), VAR(40, null);

    private final int priority;
    private String symbol;

    Lexeme(int priority, String symbol) {
        this.priority = priority;
        this.symbol = symbol;
    }

    public int getPriority() {
        return priority;
    }

    public void setSymbol(String symbol) {
        if (this == CONST || this == VAR) {
            this.symbol = symbol;
        } else {
            throw new IllegalCallerException("Cannot set symbol for operation " + this);
        }
    }

    public String getSymbol() {
        return symbol;
    }

    public static int getMaxPriority() {
        return VAR.priority;
    }

    public int arity() {
        switch (this) {
            case NEGATE:
            case ABS:
            case SQRT:
                return 1;
            case ADD:
            case SUB:
            case MUL:
            case DIV:
                return 2;
            default:
                return 0;
        }
    }
}
