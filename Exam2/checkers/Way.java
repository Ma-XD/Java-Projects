package checkers;

import java.util.ArrayList;
import java.util.Arrays;

public enum Way {
    UpLeft, UpRight,
    DownLeft, DownRight;

    public static ArrayList<Way> toList() {
        return new ArrayList<>(Arrays.asList(
                Way.UpLeft, Way.UpRight,
                Way.DownLeft, Way.DownRight
        ));
    }
}
