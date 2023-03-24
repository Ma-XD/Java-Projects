package game;

import java.util.Arrays;
import java.util.Map;

public class Board implements Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private Cell[][] cells;
    private Cell turn;
    private final int lengthOfBoard, heightOfBoard, winningNumber;
    private int countOccupiedCells = 0;

    public Board(int lengthOfBoard, int heightOfBoard, int winningNumber) {
        this.lengthOfBoard = lengthOfBoard;
        this.heightOfBoard = heightOfBoard;
        this.winningNumber = winningNumber;
        cells = new Cell[heightOfBoard][lengthOfBoard];
        for (int row = 0; row < heightOfBoard; row++) {
            Arrays.fill(cells[row], Cell.E);
        }
        turn = Cell.X;
    }

    public int getLengthOfBoard() {
        return heightOfBoard;
    }

    public int getHeightOfBoard() {
        return heightOfBoard;
    }

    public Cell getCell(final int row, final int col) {
        return cells[row][col];
    }

    public Cell getTurn() {
        return turn;
    }

    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        if(++countOccupiedCells == lengthOfBoard * heightOfBoard) {
            return Result.DRAW;
        }

        int row = move.getRow(), col = move.getColumn();
        cells[row][col] = move.getValue();
        if (score(row, col, 1, 1) == winningNumber ||
                score(row, col, 1, 0) == winningNumber ||
                score(row, col, 0, 1) == winningNumber) {
            return Result.WIN;
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    public int score(int row, int col, int rowChange, int colChange) {
        int score = 1;
        int inRow = row, inCol = col;
        while ( (inRow < heightOfBoard - 1 || rowChange < 1)
                && (inCol < lengthOfBoard - 1 || colChange < 1)
                && score < winningNumber) {
            inRow += rowChange;
            inCol += colChange;
            if(cells[inRow][inCol] == turn) {
                score++;
            } else {
                break;
            }
        }
        inRow = row;
        inCol = col;
        while ((inRow > 0 || rowChange < 1)
                && (inCol > 0 || colChange < 1)
                && score < winningNumber) {
            inRow -= rowChange;
            inCol -= colChange;
            if(cells[inRow][inCol] == turn) {
                score++;
            } else {
                break;
            }
        }
        return score;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder("  ");
        for (int col = 0; col < lengthOfBoard; col++) {
            sb.append(col + 1);
        }
        for (int row = 0; row < heightOfBoard; row++) {
            sb.append("\n");
            sb.append(row + 1).append(" ");
            for (int col = 0; col < lengthOfBoard; col++) {
                sb.append(SYMBOLS.get(cells[row][col]));
            }
        }
        return sb.toString();
    }

    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < heightOfBoard
                && 0 <= move.getColumn() && move.getColumn() < lengthOfBoard
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getTurn();
    }
}
