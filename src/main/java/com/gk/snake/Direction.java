package com.gk.snake;

public enum Direction {

    LEFT(-1, 0), RIGHT(1, 0), UP(0, -1), DOWN(0, 1);

    private final int xDelta;
    private final int yDelta;

    Direction(int xDelta, int yDelta) {
        this.xDelta = xDelta;
        this.yDelta = yDelta;
    }

    public static Direction getOpposite(Direction direction) {
        if (direction == null) {
            return null;
        }
        switch (direction) {
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            default:
                throw new RuntimeException("No opposite direction defined for " + direction);
        }
    }

    public static boolean areOpposite(Direction first, Direction second) {
        return getOpposite(first) == second;
    }

    public int getXDelta() {
        return xDelta;
    }

    public int getYDelta() {
        return yDelta;
    }
}
