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
        for (int x = x1; x < x2 + 1; x++) {
            for (int y = y1; y < y2 + 1; y++) {
                for (int z = z1; z < z2 + 1; z++) {
                    try {
                        result[x - x1][y - y1][z - z1] = expr.evaluate(getConst(type, x), getConst(type, y), getConst(type, z));
                    } catch (ExpressionException | ParseException ignored) {}
                }
            }
        }
        return result;
    }

    private <T> T getConst(TypeOperations<T> type, int i) {
        return type.cnst(Integer.toString(i));
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            throw new IllegalArgumentException("You should run this file with args <type> <expression>\n" +
                    "<-i> - int\n" +
                    "<-d> - double\n" +
                    "<-bi> - BigInteger\n" +
                    "<-u> - unchecked int\n" +
                    "<-l> - long\n" +
                    "<-s> - short\n");
        }
        String mode = args[0].substring(1);
        String expression = args[1];
        Tabulator tabulator = new GenericTabulator();
        Object[][][] result = tabulator.tabulate(mode, expression, -2, 2, -2, 2, -2, 2);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                for (int k = 0; k < result.length; k++) {
                    System.out.print(result[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}