package checkers;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;

public class Board {
    private static final Map<Checker, Character> CHECKERS = Map.of(
            Checker.BChr, 'O',
            Checker.WChr, '0',
            Checker.Empty, '_'
    );
    private static final Map<Colour, Character> CELLSCOLOURS = Map.of(
            Colour.BCell, 'B',
            Colour.WCell, 'W'
    );

    private final Cell[][] cells;
    private final int size;
    private Checker turn;
    private int countBlack;
    private int countWhite;

    public Board(final int size) {
        if (size < 8) {
            throw new InputMismatchException();
        }
        this.size = size;
        cells = new Cell[size][size];
        boolean black = true;
        for (int row = size - 1; row >= 0; row--) {
            for (int col = 0; col < size; col++) {
                Colour colour = black ? Colour.BCell : Colour.WCell;
                cells[row][col] = new Cell(colour);
                black = !black;
            }
            black = (size % 2 == 0) != black;
        }
        turn = Checker.BChr;
    }

    public void makeStartPositions() {
        fillRow(0, Checker.BChr);
        fillRow(1, Checker.BChr);
        fillRow(2, Checker.BChr);
        fillRow(size - 3, Checker.WChr);
        fillRow(size - 2, Checker.WChr);
        fillRow(size - 1, Checker.WChr);
    }

    private void fillRow(final int row, Checker checker) {
        for (int col = 0; col < size; col++) {
            if (cells[row][col].colour == Colour.BCell) {
                cells[row][col].checker = checker;
                if (checker == Checker.BChr) {
                    countBlack++;
                } else {
                    countWhite++;
                }
            }
        }
    }

    public Result makeMove(Move move) {
        LocationPair pair = getLocation(move);
        int row = pair.row;
        int col = pair.col;
        if (row == move.row && col == move.col) {
            return Result.ERROR;
        }
        for (int i = move.row + move.dRow; i != row; i += move.dRow) {
            for (int j = move.col + move.dCol; j != col; j += move.dCol) {
                cells[i][j].checker = Checker.Empty;
            }
            if (turn == Checker.BChr) {
                countWhite--;
            } else {
                countBlack--;
            }
        }
        cells[move.row][move.col].checker = Checker.Empty;
        cells[row][col].checker = turn;
        turn = turn == Checker.BChr ? Checker.WChr : Checker.BChr;
        return checkResult();
    }

    private LocationPair getLocation(Move move) {
        int row = move.row;
        int col = move.col;
        LocationPair old = new LocationPair(move.row, move.col);
        if (!isInside(col, row) || cells[row][col].checker != turn) {
            return old;
        }
        do {
            col += move.dCol;
            row += move.dRow;
            if (!isInside(col, row)) {
                return old;
            }
            if (cells[row][col].checker == turn) {
                return old;
            }
        } while (cells[row][col].checker != Checker.Empty);
        return new LocationPair(row, col);
    }

    private Result checkResult() {
        if (countWhite == 0 || countBlack == 0) {
            return Result.LOSE;
        }
        ArrayList<Way> ways = Way.toList();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (cells[row][col].checker == turn) {
                    for (Way way : ways) {
                        LocationPair pair = getLocation(new Move(row, col, way));
                        if (row != pair.row || col != pair.col) {
                            return Result.UNKNOWN;
                        }
                    }
                }
            }
        }
        return Result.LOSE;
    }


    private boolean isInside(int row, int col) {
        return 0 <= row && row < size
                && 0 <= col && col < size;
    }

    public int getSize() {
        return size;
    }

    public Checker getTurn() {
        return turn;
    }

    public String getCount() {
        return "Black checkers: " + countBlack + "\n" +
                "White checkers: " + countWhite;
    }

    public Cell getCell(final int row, final int col) {
        return cells[row][col];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("   ");
        for (int col = 0; col < size; col++) {
            sb.append(col + 1).append(".");
        }
        for (int row = 0; row < size; row++) {
            sb.append("\n");
            sb.append(row + 1).append(". ");
            for (int col = 0; col < size; col++) {
                sb.append(CHECKERS.get(cells[row][col].checker)).append(' ');
            }
        }
        return sb.toString();
    }

}
