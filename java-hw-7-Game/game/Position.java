package game;

public interface Position {
    Cell getCell(int row, int col);
    boolean isValid(Move move);
}
