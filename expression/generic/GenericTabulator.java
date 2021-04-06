package expression.generic;

import expression.ExpressionException;
import expression.TripleExpression;
import expression.parser.ExpressionParser;
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
            case "s":
                return new ShortOperations();
            default:
                throw new IllegalArgumentException("Unknown type for mode \"" + mode + "\"");
        }
    }

    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        TypeOperations<?> type = getType(mode);
        return buildTable(type, expression, x1, x2, y1, y2, z1, z2);
    }

    private <T> Object[][][] buildTable(TypeOperations<T> type, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        Object[][][] result = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        try {
            TripleExpression<T> expr = new ExpressionParser<>(type).parse(expression);
            for (int dx = 0; dx < x2 - x1 + 1; dx++) {
                for (int dy = 0; dy < y2 - y1 + 1; dy++) {
                    for (int dz = 0; dz < z2 - z1 + 1; dz++) {
                        try {
                            result[dx][dy][dz] = expr.evaluate(getConst(type, dx + x1), getConst(type, dy + y1), getConst(type, dz + z1));
                        } catch (ExpressionException ignored) {}
                    }
                }
            }
        } catch (ParseException ignored) {}
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