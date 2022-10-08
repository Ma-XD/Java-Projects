package game;

public class Move {
    private final int row;
    private final int col;
    private final Cell value;

    public Move(final int row, final int column, final Cell value) {
        this.row = row;
        this.col = column;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return col;
    }

    public Cell getValue() {
        return value;
    }

    public String toString() {
        return "row=" + (row + 1) + ", column=" + (col + 1) + ", value=" + value;
    }
}
