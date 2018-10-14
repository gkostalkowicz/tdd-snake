package com.gk.snake.logic.domain.snake;

import com.gk.snake.logic.domain.Direction;
import com.gk.snake.logic.domain.XY;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class SnakePositionUpdater {

    @RequiredArgsConstructor
    @Getter
    public static class SnakePositionUpdate {

        private final List<XY> position;
        private final boolean appleEaten;
    }

    public SnakePositionUpdate getNextPosition(List<XY> currentPosition, Direction direction, XY applePosition) {
        List<XY> position = new ArrayList<>(currentPosition);

        // add new head
        position.add(0, new XY(position.get(0), direction.getXDelta(), direction.getYDelta()));

        boolean snakeBodyOverlapsWithApple = snakeBodyOverlapsWithApple(position, applePosition);

        // remove old tail
        if (!snakeBodyOverlapsWithApple) {
            position.remove(position.size() - 1);
        }

        return new SnakePositionUpdate(position, snakeBodyOverlapsWithApple);
    }

    private boolean snakeBodyOverlapsWithApple(List<XY> body, XY applePosition) {
        return body.stream()
                .anyMatch(bodyXy -> bodyXy.equals(applePosition));
    }
}
