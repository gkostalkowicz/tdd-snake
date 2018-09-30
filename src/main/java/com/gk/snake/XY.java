package com.gk.snake;

public class XY {

    private final int x;
    private final int y;

    public XY(XY xy, int xDelta, int yDelta) {
        this.x = xy.x + xDelta;
        this.y = xy.y + yDelta;
    }

    public XY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof XY)) {
            return false;
        }
        XY xy = (XY) obj;
        return x == xy.x && y == xy.y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
