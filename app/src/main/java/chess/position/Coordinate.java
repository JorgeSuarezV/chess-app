package chess.position;

import org.jetbrains.annotations.NotNull;

public class Coordinate implements Comparable<Coordinate>{

    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate addXOne(){
        return new Coordinate(x + 1 , y);
    }
    public Coordinate takeXOne(){
        return new Coordinate(x - 1, y);
    }
    public Coordinate addYOne(){
        return new Coordinate(x, y + 1);
    }
    public Coordinate takeYOne(){
        return new Coordinate(x, y - 1);
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
