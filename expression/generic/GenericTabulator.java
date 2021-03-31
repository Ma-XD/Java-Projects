package expression.generic;

import expression.ExpressionException;
import expression.TripleExpression;
import expression.exceptions.ExpressionParser;
import expression.exceptions.ParseException;
import expression.types.*;


public class GenericTabulator implements Tabulator {

    private TypeOperations<?> getType(String mode) {
        switch (mode) {
            case "i":
                return new IntegerOperations();
            case "d":
                return new DoubleOperations();
            case "bi":
                return new BigIntegerOperations();
            case "u":
                return new UncheckedIntegerOperations();
            case "l":
                return new LongOperations();
            case  "s":
                return new ShortOperations();
            default:
                throw new IllegalArgumentException("Unknown type for letter \"" + mode + "\"");
        }
    }

    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        TypeOperations<?> type = getType(mode);
        return buildTable(type, expression, x1, x2, y1, y2, z1, z2);
    }

    private <T> Object[][][] buildTable(TypeOperations<T> type, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        Object[][][] result = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        TripleExpression<T> expr = new ExpressionParser<>(type).parse(expression);
        for (int i = x1; i < x2 + 1; i++) {
            for (int j = y1; j < y2 + 1; j++) {
                for (int k = z1; k < z2 + 1; k++) {
                    try {
                        result[i - x1][j - y1][k - z1] = expr.evaluate(getConst(type, i), getConst(type, j), getConst(type, k));
                    } catch (ExpressionException | ParseException ignored) {}
                }
            }
        }
        return result;
    }

    private <T> T getConst(TypeOperations<T> type, int i) {
        return type.cnst(Integer.toString(i));
    }
}


