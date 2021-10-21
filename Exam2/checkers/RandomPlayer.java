package checkers;

import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random = new Random();

    @Override
    public Result makeMove(Board board) {
        while (true) {
            int row = random.nextInt(board.getSize());
            int col = random.nextInt(board.getSize());
            int number = random.nextInt(4);
            ArrayList<Way> ways = Way.toList();
            Result result = board.makeMove(new Move(row, col, ways.get(number)));
            if (result != Result.ERROR) {
                System.out.println("Move: row - " + (row + 1) +
                        ", column - " + (col + 1) +
                        ", way - " + ways.get(number));
                return result;
            }
        }
    }
}
