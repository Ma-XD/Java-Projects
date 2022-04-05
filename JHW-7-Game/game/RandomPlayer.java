package game;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random = new Random();

    @Override
    public Move makeMove(final Position position, final Cell cell, final int lengthOfBoard, final int heightOfBoard) {
        while (true) {
            int row = random.nextInt(heightOfBoard);
            int col = random.nextInt(lengthOfBoard);
            final Move move = new Move(row, col, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
