package game;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner in;

    public HumanPlayer(final Scanner in) {
        this.in = in;
    }

    public HumanPlayer() {
        this(new Scanner(System.in));
    }

    public int correctInput() {
        int pos = -1;
        if (in.hasNextInt()) {
            pos = in.nextInt() - 1;
            return pos;
        }
        String str = in.next();
        return pos;
    }

    @Override
    public Move makeMove(final Position position, final Cell cell, final int lengthOfBoard, final int heightOfBoard) {
        System.out.println("Position");
        System.out.println(position);
        System.out.println(cell + "'s move");
        System.out.println("Enter row from 1 to " + heightOfBoard + "\nand column from 1 to " + lengthOfBoard);
        while (true) {
            int row = correctInput(), col = correctInput();
            if (row < 0 || col < 0 || row > heightOfBoard - 1 || col > lengthOfBoard) {
                System.out.println("Incorrect input format.\n Try again");
                continue;
            }
            final Move move = new Move(row, col, cell);
            if (position.isValid(move)) {
                return move;
            }
            System.out.println("Cell " + move + " is taken.\n Try again");
        }
    }
}
