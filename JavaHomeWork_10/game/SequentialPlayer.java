package game;

public class SequentialPlayer implements Player {
    public Move makeMove(final Position position, final Cell cell, final int lengthOfBoard, final int heightOfBoard) {
        for (int row = 0; row < lengthOfBoard; row++) {
            for (int col = 0; col < heightOfBoard; col++) {
                if (position.getCell(row, col) == Cell.E) {
                    return new Move(row, col, cell);
                }
            }
        }
        throw new IllegalStateException("No valid moves");
    }
}
