package checkers;

public class Cell {
    public final Colour colour;
    public Checker checker;

    public Cell(Colour colour) {
        this.colour = colour;
        this.checker = Checker.Empty;
    }

    @Override
    public String toString() {
        return "[" +
                colour +
                ", " +
                checker +
                ']';
    }
}
