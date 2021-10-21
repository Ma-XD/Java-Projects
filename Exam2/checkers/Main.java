package checkers;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(new HumanPlayer(), new RandomPlayer());
        game.play();
    }
}
