package game;

import java.util.Scanner;

public class Checker {
    private final Scanner in;

    public Checker(Scanner in) {
        this.in = in;
    }

    public int check() {
        String rubbish;
        int i;
        while (true) {
            if (in.hasNextInt()) {
                i = in.nextInt();
                if (i > 0) {
                    return i;
                }
            } else {
                rubbish = in.next();
            }
            System.out.println("It's not a correct number.\nTry again.");
        }
    }
}
