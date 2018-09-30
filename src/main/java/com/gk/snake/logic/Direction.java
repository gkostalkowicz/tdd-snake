package com.gk.snake.logic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Direction {

    LEFT(-1, 0), RIGHT(1, 0), UP(0, -1), DOWN(0, 1);

    private final int xDelta;
    private final int yDelta;

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
}
