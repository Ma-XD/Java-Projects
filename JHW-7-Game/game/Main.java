package game;

import java.util.Scanner;

public class  Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int players, circles, n, m, k;
        Checker c = new Checker(sc);

        System.out.println("Enter number of players:");
        players = c.check();
        System.out.println("Enter number of circles:");
        circles = c.check();
        System.out.println("Enter n:");
        n = c.check();
        System.out.println("Enter m:");
        m = c.check();
        System.out.println("Enter k:");
        k = c.check();

        Tournament tournament = new Tournament(players, circles, n, m, k);
        tournament.play();
        System.out.println(tournament.toString());
    }
}
