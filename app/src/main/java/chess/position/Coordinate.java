package chess.position;

import org.jetbrains.annotations.NotNull;

public class Coordinate implements Comparable<Coordinate>{

    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public int compareTo(@NotNull Coordinate o) {
        if (o.getY() == y && o.getX() == x) return 0;
        return 1;
    }
}
