package checkers;

import java.util.Map;

public class Game {
    private final Player player1;
    private final Player player2;
    private final Board board = new Board(8);

    private final Map<Checker, String> toStrChr = Map.of(
            Checker.BChr, "Black",
            Checker.WChr, "White"
    );

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    private void showBoard() {
        System.out.println("==================" + "\n" +
                board + "\n" +
                "==================" + "\n" +
                board.getCount() + "\n");
    }

    public void play() {
        board.makeStartPositions();
        showBoard();
        Player player = player1;
        int n = 1;
        while (true) {
            int res = getResult(player);
            if (res != 0) {
                if (res == 2) {
                    System.out.println("\n" + "Player" + n + " wins!" + "\n");
                }
                return;
            }
            player = (player == player1) ? player2 : player1;
            n = (n == 1) ? 2 : 1;
        }
    }

    private int getResult(Player player) {
        System.out.println(toStrChr.get(board.getTurn()) + "'s move:");
        Result result = player.makeMove(board);
        if (result == Result.LOSE) {
            showBoard();
            System.out.println("\n" + "==================" +
                    "\n" + toStrChr.get(board.getTurn()) + "'s lose" +
                    "\n" + "==================");
            return 2;
        } else if (result == Result.EXIT) {
            return 1;
        } else {
            showBoard();
            return 0;
        }
    }
}
