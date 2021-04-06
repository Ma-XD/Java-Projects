package expression.parser;

import expression.*;
import expression.exceptions.*;
import expression.types.TypeOperations;

public class ExpressionParser<T> implements Parser<T> {
    private final TypeOperations<T> type;

    public ExpressionParser(TypeOperations<T> type) {
        this.type = type;
    }

    private TypeOperations<T> getType() {
        return type;
    }

    public CommonExpression<T> parse(final String expression) {
        return new CheckedExpressionParser<T>(type, new StringSource(expression)).checkedParse();
    }

    private static class CheckedExpressionParser<T> extends BaseParser {
        protected TypeOperations<T> type;
        private CommonExpression<T> prevExpression;
        private boolean wasBracketClosed;
        private Lexeme prevLexeme = null;
        private int countBrackets = 0;

        private CheckedExpressionParser(TypeOperations<T> type, final CharSource source) {
            super(source);
            this.type = type;
        }

        private CommonExpression<T> checkedParse() {
            CommonExpression<T> expression = parseExpression(0);
            if (source.hasNext() || countBrackets != 0) {
                throw new BracketException("Different number of open and closed brackets");
            }
            return expression;
        }

        private CommonExpression<T> parseExpression(int prevPriority) {
            boolean wasBracketOpen = false;
            while (source.hasNext()) {
                if (!wasBracketOpen && wasBracketClosed) {
                    break;
                }
                wasBracketOpen = false;
                wasBracketClosed = false;

                String element = nextElement();
                Lexeme lexeme = getLexeme(element);
                if (lexeme == Lexeme.OPENED) {
                    countBrackets++;
                    wasBracketOpen = true;
                }
                checkForCorrect(lexeme);
                if (lexeme.getPriority() <= prevPriority) {
                    prevElement(element.length());
                    break;
                }
                prevLexeme = lexeme;
                wrapping(lexeme);
            }
            return prevExpression;
        }

        private void checkForCorrect(Lexeme lexeme) {
            int pos = source.getPos() - lexeme.getSymbol().length();
            if (prevLexeme != null &&
                    prevLexeme != Lexeme.OPENED &&
                    lexeme != Lexeme.CLOSED &&
                    !isOperation(prevLexeme.getSymbol()) &&
                    lexeme.getPriority() == Lexeme.getMaxPriority() &&
                    (lexeme.arity() == 2 || lexeme.arity() == 0))  {
                throw new IllegalOperationException("Two elements in a row without operation on pos: " + pos);
            }
            if (prevLexeme == Lexeme.OPENED &&
                    lexeme == Lexeme.CLOSED) {
                throw new BracketException("Two different brackets in a row on pos: " + pos);
            }
            if (isOperation(lexeme.getSymbol()) &&
                    lexeme != Lexeme.OPENED &&
                    lexeme.arity() == 2 &&
                    prevExpression == null) {
                throw new IllegalOperationException("Binary operation at the beginning of the line");
            }
            if (isOperation(lexeme.getSymbol())  &&
                    lexeme != Lexeme.CLOSED &&
                    !source.hasNext() ||
                            lexeme.getSymbol().length() == 0) {
                throw new IllegalOperationException("Operation at the end of the line");
            }
            if (prevLexeme != null &&
                    isOperation(prevLexeme.getSymbol()) &&
                    prevLexeme != Lexeme.CLOSED &&
                    (lexeme.getPriority() < Lexeme.getMaxPriority() ||
                            lexeme == Lexeme.CLOSED)) {
                throw new IllegalOperationException("Two illegal operations in a row on pos: " + pos);
            }
        }

        public Lexeme getLexeme(String symbol) {
            for(Lexeme l: Lexeme.values()) {
                if (l.getSymbol() != null && l.getSymbol().equals(symbol)) {
                    return isUnaryMinus(symbol) ? Lexeme.NEGATE : l;
                }
            }
            if (isNumber(symbol)) {
                Lexeme lexeme = Lexeme.CONST;
                lexeme.setSymbol(symbol);
                return lexeme;

            }
            if (isVar(symbol)) {
                Lexeme lexeme = Lexeme.VAR;
                lexeme.setSymbol(symbol);
                return lexeme;
            }
            throw new VariableException(
                    "Unacceptable element on pos: " +
                    (source.getPos() - symbol.length())
            );
        }

        private boolean isVar(String element) {
            switch (element) {
                case "x":
                case "y":
                case "z":
                    return true;
            }
            return false;
        }

        private boolean isNumber(String str) {
            char[] c = str.toCharArray();
            int countPoints = 0;
            for (int i = 0; i < c.length; i++) {
                if (!(Character.isDigit(c[i]) ||
                        c[i] == '-' && i == 0 ||
                        c[i] == '.' && i > 0 && countPoints == 0)) {
                    return false;
                }
                if (c[i] == '.') {
                    countPoints++;
                }
            }
            return true;
        }

        private void wrapping(Lexeme lexeme) {
            switch (lexeme) {
                case ADD:
                    prevExpression = new Add<T>(type, prevExpression, parseExpression(lexeme.getPriority()));
                    return;
                case SUB:
                    prevExpression = new Subtract<T>(type, prevExpression, parseExpression(lexeme.getPriority()));
                    return;
                case MUL:
                    prevExpression = new Multiply<T>(type, prevExpression, parseExpression(lexeme.getPriority()));
                    return;
                case DIV:
                    prevExpression = new Divide<T>(type, prevExpression, parseExpression(lexeme.getPriority()));
                    return;
                case OPENED:
                    prevExpression = parseExpression(0);
                    return;
                case CLOSED:
                    countBrackets--;
                    wasBracketClosed = true;
                    return;
                case NEGATE:
                    prevExpression = new Negate<T>(type, parseExpression(lexeme.getPriority() - 1));
                    return;
                case CONST:
                    prevExpression = new Const<T>(type, lexeme.getSymbol());
                    return;
                case VAR:
                    prevExpression = new Variable<T>(lexeme.getSymbol());
                    return;

            }
        }

        private void prevElement(int n) {
            for (int j = 0; j < n;) {
                if (!Character.isWhitespace(ch)) {
                    j++;
                }
                prevChar();
            }
        }

        private void deleteSpaces() {
            if (Character.isWhitespace(ch)) {
                skipWhitespace();
                if (ch != END) {
                    prevChar();
                }
            }
        }

        private void skipWhitespace() {
            while (test(' ') || test('\r') || test('\n') || test('\t')) {
                // skip
            }
        }

        private boolean isUnaryMinus(String element) {
            return  (prevLexeme == null || isOperation(prevLexeme.getSymbol()) &&
                    prevLexeme != Lexeme.CLOSED ) &&
                    element.equals(Lexeme.NEGATE.getSymbol());
        }

        private boolean isOperation(String element) {
            for (Lexeme l: Lexeme.values()) {
                if (element.equals(l.getSymbol()) && l != Lexeme.CONST && l != Lexeme.VAR)
                    return true;
            }
            return false;
        }

        private String nextElement() {
            StringBuilder sb = new StringBuilder();
            while (true) {
                nextChar();
                if (ch == END) {
                    break;
                }
                if (isOperation(Character.toString(ch))) {
                    if (isUnaryMinus(Character.toString(ch)) && sb.length() == 0) {
                        sb.append(ch);
                        nextChar();
                        if (!Character.isDigit(ch)) {
                            if (ch != END) {
                                prevChar();
                            }
                            break;
                        }
                    } else {
                        if (sb.length() == 0) {
                            return Character.toString(ch);
                        }
                        prevChar();
                        break;
                    }
                }
                if(Character.isWhitespace(ch)) {
                    if(sb.length() > 0) {
                        break;
                    }
                } else {
                    sb.append(ch);
                }
            }
            deleteSpaces();
            return sb.toString();
        }
    }
}
