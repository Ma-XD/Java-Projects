package checkers;

import java.util.Map;

public class Move {
    public final int row;
    public final int col;
    public int dRow;
    public int dCol;

    private static final Map<Way, LocationPair> ways = Map.of(
            Way.UpLeft, new LocationPair(-1, -1),
            Way.UpRight, new LocationPair(-1, 1),
            Way.DownLeft, new LocationPair(1, -1),
            Way.DownRight, new LocationPair(1, 1)
    );

    public Move(int row, int col, Way way) {
        this.col = col;
        this.row = row;
        dRow = ways.get(way).row;
        dCol = ways.get(way).col;
    }
}
