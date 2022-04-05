package game;

public interface Player {
    Move makeMove(Position position, Cell cell, int lengthOfBoard, int heightOfBoard);
}
