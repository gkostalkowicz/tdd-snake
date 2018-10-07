package com.gk.snake.logic.domain;

import com.gk.snake.KeyStroke;
import com.gk.snake.logic.SnakeDirectionUpdater;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Snake {

    private final List<XY> body;
    private Direction direction;
    private final SnakeDirectionUpdater snakeDirectionUpdater;

    public Snake(List<XY> body, Direction direction, SnakeDirectionUpdater snakeDirectionUpdater) {
        this.body = Collections.unmodifiableList(new ArrayList<>(body));
        this.direction = direction;
        this.snakeDirectionUpdater = snakeDirectionUpdater;
    }

    public void updateSnakeDirection(KeyStroke keyStroke) {
        this.direction = snakeDirectionUpdater.getNextDirection(direction, keyStroke);
    }
}
