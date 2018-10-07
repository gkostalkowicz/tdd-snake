package com.gk.snake.logic.domain;

import com.gk.snake.KeyStroke;
import com.gk.snake.logic.SnakeDirectionUpdater;
import com.gk.snake.logic.SnakePositionUpdater;
import com.gk.snake.logic.SnakePositionUpdater.SnakePositionUpdate;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Snake {

    private List<XY> body;
    private Direction direction;
    private final SnakeDirectionUpdater snakeDirectionUpdater;
    private final SnakePositionUpdater snakePositionUpdater;

    public Snake(List<XY> body, Direction direction, SnakeDirectionUpdater snakeDirectionUpdater,
                 SnakePositionUpdater snakePositionUpdater) {
        this.body = Collections.unmodifiableList(new ArrayList<>(body));
        this.direction = direction;
        this.snakeDirectionUpdater = snakeDirectionUpdater;
        this.snakePositionUpdater = snakePositionUpdater;
    }

    public boolean updateForNextFrame(KeyStroke keyStroke, XY applePosition) {
        this.direction = snakeDirectionUpdater.getNextDirection(direction, keyStroke);

        SnakePositionUpdate positionUpdate = snakePositionUpdater.getNextPosition(body, direction, applePosition);
        this.body = positionUpdate.getPosition();

        return positionUpdate.isAppleEaten();
    }
}
