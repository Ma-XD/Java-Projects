package checkers;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner in;
    private final Map<String, Way> GO = Map.of(
            "UL", Way.UpLeft, "UR", Way.UpRight,
            "DL", Way.DownLeft, "DR", Way.DownRight
    );

    public HumanPlayer() {
        this.in = new Scanner(System.in);
        showInterface();
    }

    public HumanPlayer(final Scanner in) {
        this.in = in;
        showInterface();
    }

    public void showInterface() {
        System.out.println("Game Interface: " + "\n" +
                "To make move you should write row and column of checker and way where you want to move it" + "\n" +
                "Ways:" + "\n" + "Write 'UL' to move Up and Left" + "\n" +
                "UR - up and right, DL - down and left, DR - down and right" + "\n" +

                "==================" + "\n" +
                "Black starts:");
    }

    private void invalidInput() {
        System.out.println("\n" + "*Invalid move, try again*" + "\n");
    }

    private void exit() {
        System.out.println("\n" + "*Exit*" + "\n");
    }

    @Override
    public Result makeMove(Board board) {
        while (true) {
            System.out.println("Write row, column and way");
            int row, col;
            Way way;
            try {
                row = in.nextInt() - 1;
                col = in.nextInt() - 1;
                String str = in.next();
                if (GO.containsKey(str)) {
                    way = GO.get(str);
                } else {
                    invalidInput();
                    continue;
                }
            } catch (InputMismatchException e) {
                if (in.next().equals("EXIT")) {
                    exit();
                    return Result.EXIT;
                } else {
                    invalidInput();
                    continue;
                }
            }
            Result result = board.makeMove(new Move(row, col, way));
            if (result == Result.ERROR) {
                invalidInput();
            } else {
                return result;
            }
        }
    }
}
