package com.gk.snake.logic.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GameState {

    private final Snake snake;

    private final XY applePosition;

    private final GameStatus gameStatus;

    public GameState(Snake snake, XY applePosition) {
        this(snake, applePosition, GameStatus.PLAYING);
    }
}
