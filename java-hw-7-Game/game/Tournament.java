package game;

import java.util.Arrays;

public class Tournament {
    private final int playersNumber, circlesNumber;
    private final int lengthOfBoard, heightOfBoard, winningNumber;
    private int[] resultsTable;

    public Tournament(int playersNumber, int circlesNumber, int lengthOfBoard, int heightOfBoard, int winningNumber) {
        this.playersNumber = playersNumber;
        this.circlesNumber = circlesNumber;
        this.lengthOfBoard = lengthOfBoard;
        this.heightOfBoard = heightOfBoard;
        this.winningNumber = winningNumber;
        resultsTable = new int[playersNumber];
        Arrays.fill(resultsTable, 0);
    }

    public void play() {
        for (int c = 0; c < circlesNumber; c++) {
            System.out.println("\n Circle #" + (c + 1) + "\n");
            circle();
        }
    }

    private int[][] createTable() {
        int [][] table = new int[playersNumber][playersNumber];
        for (int i = 0; i < playersNumber; i++) {
            Arrays.fill(table[i], 0);
        }
        return table;
    }

    private void circle() {
        int[][] gamesTable = createTable();
        int count = 0;
        for (int i = 0; i < playersNumber; i++) {
            for (int j = 0; j < playersNumber; j++) {
                if ( i != j && gamesTable[i][j] == 0) {
                    System.out.println("\n game #" + (++count) + "\n Player" + (i + 1) + " and Player" + (j + 1) + "\n");
                    Game newGame = new Game(true, new RandomPlayer(), new RandomPlayer());
                    int res = newGame.play(new Board(lengthOfBoard, heightOfBoard, winningNumber));
                    switch (res) {
                        case 1:
                            resultsTable[i]+= 3;
                            break;
                        case 2:
                            resultsTable[j] += 3;
                            break;
                        case 0:
                            resultsTable[i]++;
                            resultsTable[j]++;
                            break;
                    }
                    gamesTable[i][j] = 1;
                    gamesTable[j][i] = 1;
                }
            }
        }
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < playersNumber; i++) {
            sb.append("Player" + (i + 1) + "'s score: " + resultsTable[i]).append('\n');
        }
        return sb.toString();
    }
}
